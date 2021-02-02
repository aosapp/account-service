package com.advantage.accountrest.api;

import com.advantage.accountrest.AccountserviceClient.AccountLoginResponse;
import com.advantage.accountrest.AccountserviceClient.AccountServicePort;
import com.advantage.accountrest.AccountserviceClient.AccountServicePortService;
import com.advantage.accountsoap.config.SmokingGunInit;
import com.advantage.accountsoap.dto.account.*;
import com.advantage.accountsoap.services.AccountService;
import com.advantage.accountsoap.util.UrlResources;
import com.advantage.common.Constants;
import com.advantage.common.cef.CefHttpModel;
import com.advantage.common.enums.AccountType;
import com.advantage.common.exceptions.authorization.AuthorizationException;
import com.advantage.common.security.AuthorizeAsAdmin;
import com.advantage.common.security.SecurityTools;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Binyamin Regev on 20/12/2015.
 */
@RestController
@RequestMapping(value = "accountrest" + Constants.URI_API + "/v1")
public class AccountServiceController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = Logger.getLogger(AccountServiceController.class);

    @ModelAttribute
    public void setResponseHeaderForAllRequests(HttpServletResponse response) {
        response.setHeader("Expires", "0");
        response.setHeader("Cache-control", "no-store");
    }


    /**
     * @param request
     * @param response
     * @return {String}
     */
    @RequestMapping(value = "/health-check", method = RequestMethod.GET)
    @ApiOperation(value = "Get detailed information about the health of the service")
    public ResponseEntity<String> getHealthCheck(HttpServletRequest request,
                                                 HttpServletResponse response) {
        setCefLogData(request,"healthcheck");

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login to AOS")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class)})
    public ResponseEntity<AccountLoginResponse> doLogin(HttpServletRequest request,
                                                 HttpServletResponse response,
                                          @RequestBody AccountLoginRequest loginRequest) throws MalformedURLException {
        setCefLogData(request,"login");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        AccountLoginResponse alr = new AccountLoginResponse();
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            alr = asPort.accountLogin(loginRequest);
            if(alr.getStatusMessage().isSuccess())
                return new ResponseEntity<>(alr, HttpStatus.OK);
            else{
                if(alr.getStatusMessage().getReason().contains("Incorrect user name")){
                    return new ResponseEntity<>(alr, HttpStatus.FORBIDDEN);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            AccountLoginResponse.StatusMessage asm = new AccountLoginResponse.StatusMessage();
            asm.setSuccess(false);
            asm.setReason(e.getMessage());
            alr.setStatusMessage(asm);
            return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new User account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLoginResponse.class)})
    public ResponseEntity<AccountCreateResponse> register(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @RequestBody AccountCreateRequest accountCreateRequest) throws MalformedURLException {
        setCefLogData(request,"register");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        AccountCreateResponse acr = new AccountCreateResponse();
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            acr = asPort.accountCreate(accountCreateRequest);
            if(acr.getResponse().isSuccess())
                return new ResponseEntity<>(acr, HttpStatus.OK);
            else{
                return new ResponseEntity<>(acr, HttpStatus.FORBIDDEN);

            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            acr.setResponse(createAccountStatusRes(e.getMessage()));
            return new ResponseEntity<>(acr, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "Logout from AOS")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logout successful", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class)})
    public ResponseEntity<AccountLogoutResponse> doLogout(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @RequestBody AccountLogoutRequest logoutRequest) throws MalformedURLException {
        setCefLogData(request,"logout");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        AccountLogoutResponse alr = null;
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            alr = asPort.accountLogout(logoutRequest);
            if(alr.getResponse().isSuccess())
                return new ResponseEntity<>(alr, HttpStatus.OK);
            else{
                if(alr.getResponse().getReason().contains("Incorrect user name")){
                    return new ResponseEntity<>(alr, HttpStatus.FORBIDDEN);
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();

        }
        return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @AuthorizeAsAdmin
    @RequestMapping(value = "/deactivate", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "JSON Web Token, Use the returned token value from /login request.", defaultValue = "Bearer ", example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3d3cuYWR2YW50YWdlb25saW5lc2hvcHBpbmcuY29tIiwidXNlcklkIjoxNjA0Njg2MTUsInN1YiI6ImFkbWluIiwicm9sZSI6IkFETUlOIn0.f7QRYrfpyvxKXDRhWAdocBnuldtQtj-rklmjKn1V80E")})
    @ApiOperation(value = "deactivate a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful deactivation", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class)})
    public ResponseEntity<AccountDeleteResponse> deactivateAccount(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          @RequestBody AccountDeleteRequest deleteRequest) throws MalformedURLException {
        setCefLogData(request,"deactivate");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        AccountDeleteResponse alr = new AccountDeleteResponse();
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            alr = asPort.accountDelete(deleteRequest);
            if(alr.getResponse().isSuccess())
                return new ResponseEntity<>(alr, HttpStatus.OK);
            else{
                return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            alr.setResponse(createAccountStatusRes(e.getMessage()));
            return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @AuthorizeAsAdmin
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "JSON Web Token, Use the returned token value from /login request.", defaultValue = "Bearer ", example = "Bearer xxxxxxxxxxxxxxxxxxxxxxx")})
    @ApiOperation(value = "Permanent delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful deactivation", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class)})
    public ResponseEntity<AccountPermanentDeleteResponse> deleteUser(HttpServletRequest request,
                                                                                                                    HttpServletResponse response,
                                                                                                                    @RequestBody AccountPermanentDeleteRequest deleteRequest) throws MalformedURLException {
        setCefLogData(request,"delete");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        AccountPermanentDeleteResponse alr = new AccountPermanentDeleteResponse();
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            deleteRequest.setBase64Token(request.getHeader("Authorization").split("Bearer ")[1]);
            deleteRequest.setData("Bearer " + deleteRequest.getBase64Token());
            alr = asPort.accountPermanentDelete(deleteRequest);
            if(alr.getStatusMessage().isSuccess())
                return new ResponseEntity<>(alr, HttpStatus.OK);
            else{
                return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            alr.setStatusMessage(createAccountStatusRes(e.getMessage()));
            return new ResponseEntity<>(alr, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header", value = "JSON Web Token, Use the returned token value from /login request.", defaultValue = "Bearer ", example = "Bearer xxxxxxxxxxxxxxxxxxxxxxx")})
    @ApiOperation(value = "Change user password, admins can change everyone, users can change their own")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful deactivation", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = com.advantage.accountrest.AccountserviceClient.AccountLogoutResponse.class)})
    public ResponseEntity<ChangePasswordResponse> changePassword(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestBody ChangePasswordRequest changePasswordRequest) throws MalformedURLException {
        setCefLogData(request,"change-password");
        URL urlWsdlLocation = UrlResources.getUrlSoapAccount();
        ChangePasswordResponse cpr = new ChangePasswordResponse();
        try{
            AccountServicePortService asps = new AccountServicePortService(urlWsdlLocation);
            AccountServicePort asPort = asps.getAccountServicePortSoap11();
            changePasswordRequest.setBase64Token(request.getHeader("Authorization").split("Bearer ")[1]);
            cpr = asPort.changePassword(changePasswordRequest);
            if(cpr.getResponse().isSuccess())
                return new ResponseEntity<>(cpr, HttpStatus.OK);
            else{
                return new ResponseEntity<>(cpr, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            cpr.setResponse(createAccountStatusRes(e.getMessage()));
            return new ResponseEntity<>(cpr, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Autowired
    SmokingGunInit smokingGunInit;

    @ApiIgnore
    @RequestMapping(value = "/start-smoking-gun-scenario", method = RequestMethod.GET)
    @ApiOperation(value = "Create a 'smoking gun' scenario. duplicating countries and default user, causing unstable AOS backend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Scenario activated successfully", response = String.class),
            @ApiResponse(code = 403, message = "Wrong user name or password", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error", response = String.class)})
    public ResponseEntity<String> startSmokingGunScenario(HttpServletRequest request) throws MalformedURLException {
        setCefLogData(request,"start-smoking-gun-scenario");
        try {
            logger.info("Activating smoking-gun-scenario");
            smokingGunInit.activate();
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AccountStatusResponse createAccountStatusRes(String message) {
        AccountStatusResponse asr = new AccountStatusResponse();
        asr.setSuccess(false);
        asr.setReason(message);
        return asr;
    }

    private void setCefLogData(HttpServletRequest request,String requestName) {
        CefHttpModel cefData = (CefHttpModel) request.getAttribute("cefData");
        if (cefData != null) {
            logger.trace("cefDataId=" + cefData.toString());
            cefData.setEventRequiredParameters(String.valueOf(("/" + requestName).hashCode()),
                    ("Do " + requestName), 5);
        } else {
            logger.warn("cefData is null");
        }
    }


}
