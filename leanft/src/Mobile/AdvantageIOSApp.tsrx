<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>AdvantageIOSApp</className>
    <objects>
        <object type="Application" technology="Mobile">
            <name>Ishopping</name>
            <codeName>IshoppingApplication</codeName>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.mf.iShopping</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>Menu</name>
                    <codeName>MenuButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Menu</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MAIN WINDOW</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LAPTOPS</name>
                    <codeName>LAPTOPSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LAPTOPS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">LAPTOPS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MAIN WINDOW</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>MainMenuHeadphones</name>
                    <codeName>MainMenuHeadphones</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HEADPHONES</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">HEADPHONES</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][4][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MAIN WINDOW</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PasswordSignUpEditField</name>
                    <codeName>PasswordSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">passwordTextFieldID</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ConfirmPasswordSignUpEditField</name>
                    <codeName>ConfirmPasswordSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">confirmPasswordTextFieldLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PhoneNumberTextFieldLabe</name>
                    <codeName>PhoneNumberTextFieldLabeEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">phoneNumberTextFieldLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>StreetSignUpEditField</name>
                    <codeName>StreetSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">streetTextFieldID</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CitySignUpEditField</name>
                    <codeName>CitySignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">cityTextFieldID</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CountryPickerDone</name>
                    <codeName>CountryPickerDone</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Done</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">countryPickerDone</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ZipSignUpEditField</name>
                    <codeName>ZipSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">zipTextFieldLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">17</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>KeyboardNext</name>
                    <codeName>KeyboardNext</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Next</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Next:</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>KEYBOARD</tag>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>KeyboardDone</name>
                    <codeName>KeyboardDone</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Done</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Done</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>KEYBOARD</tag>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>User name already exists1</name>
                    <codeName>UserNameAlreadyExistsLabel1</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">User name already exists</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">User name already exists</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MESSAGE</tag>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CreateAccountOkButton</name>
                    <codeName>CreateAccountOkButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">OK</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>BUTTON</tag>
                        <tag>MESSAGE</tag>
                        <tag>CREATE ACCOUNT</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>- No products in cart -</name>
                    <codeName>NoProductsInCartLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">- No products in cart -</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">- No products in cart -</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>UseMyLocationLabel</name>
                    <codeName>UseMyLocationLabel</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">locationCheckBox</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>DEL_ConfirnPasswordSignUpEditField</name>
                    <codeName>DEL_ConfirnPasswordSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">confirmPasswordTextFieldID</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>User settings</name>
                    <codeName>UserSettingsButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">User settings</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>setting</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>MainMenuHome</name>
                    <codeName>MainMenuHome</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HOME</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">HOME</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MAIN MENU</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SPEAKERS</name>
                    <codeName>SPEAKERSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SPEAKERS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">SPEAKERS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][6][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MAIN MENU</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>CART</name>
                    <codeName>CARTLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">CART</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][8][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SETTINGS</name>
                    <codeName>SETTINGSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SETTINGS</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">SETTINGS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][9][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Login Obj</name>
                    <codeName>LoginObj</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>USER NAME login</name>
                    <codeName>UserNameLoginditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">userNameLabel</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>LOGIN</name>
                    <codeName>LOGINButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">LOGIN</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>RegisterButton</name>
                    <codeName>RegisterButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">REGISTER</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">REGISTER</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>UseMylocation</name>
                    <codeName>UseMylocationUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">22</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>SignUp</name>
                    <codeName>SignUpButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Don't have an account? SIGN UP TODAY</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Invalid user name or pas</name>
                    <codeName>InvalidUserNameOrPasLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Invalid user name or password</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Invalid user name or password</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MenuObj</name>
                    <codeName>MenuObjUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>login</name>
                    <codeName>LoginLabel</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>YesSignOut</name>
                    <codeName>YesSignOutButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Yes</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Yes</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SpeakerImg</name>
                    <codeName>SpeakerImgUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][2][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>purchase</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>color</name>
                    <codeName>ColorButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">ColorButtonId</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>purchase</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ADD TO CART</name>
                    <codeName>ADDTOCARTButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD TO CART</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">ADD TO CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>carticon</name>
                    <codeName>CarticonButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">cartItem</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CHECKOUT</name>
                    <codeName>CHECKOUTButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>paymentEdit</name>
                    <codeName>PaymentEditUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ApplyQuantity</name>
                    <codeName>ApplyQuantity</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>APPLY</name>
                    <codeName>APPLYButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*SAFEPAY USERNAME</name>
                    <codeName>SAFEPAYUSERNAMEEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*SAFEPAY USERNAME</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*SAFEPAY PASSWORD</name>
                    <codeName>SAFEPAYPASSWORDEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*SAFEPAY PASSWORD</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Save SafePAy credentials</name>
                    <codeName>SaveSafePAyCredentialsLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Save SafePAy credentials in your profile</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Save SafePAy credentials in your profile</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>PAY NOW</name>
                    <codeName>PAYNOWButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">PAY NOW</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">PAY NOW</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Ok</name>
                    <codeName>OkButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Ok</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Ok</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>OrderObj</name>
                    <codeName>OrderObj</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false"></property>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CARD NUMBER</name>
                    <codeName>CARDNUMBEREditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">*CARD NUMBER</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Country</name>
                    <codeName>CountryLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Country</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Country</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>MobileDropDown</name>
                    <codeName>CountryDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CVV NUMBER</name>
                    <codeName>CVVNUMBEREditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>*CARDHOLDER NAME</name>
                    <codeName>CARDHOLDERNAMEEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Save MasterCredit creden</name>
                    <codeName>SaveMasterCreditCredenLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Save MasterCredit credentials in your profile</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Save MasterCredit credentials in your profile</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Plus</name>
                    <codeName>PlusButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Plus</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>leptop Item</name>
                    <codeName>LeptopItem</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>YearDropDown</name>
                    <codeName>YearDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="DropDown" technology="Mobile">
                    <name>MonthDropDown</name>
                    <codeName>MonthDropDown</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false"></property>
                            <property name="ClassName" type="string" isRegExp="false">ItemSelector</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>* mm</name>
                    <codeName>MmLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">* mm</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">* mm</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>* yyyy</name>
                    <codeName>YyyyLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">* yyyy</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">* yyyy</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SoldOut</name>
                    <codeName>SoldOutUiObject</codeName>
                    <description>ImageView</description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>quantity button</name>
                    <codeName>QuantityButton</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">quantityButtonId</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>User name already exists</name>
                    <codeName>UserNameAlreadyExistsLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">User name already exists</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">User name already exists</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>OK</name>
                    <codeName>OKExistingUserButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">OK</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CHANGE PASSWORD</name>
                    <codeName>CHANGEPASSWORDButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">CHANGE PASSWORD</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">CHANGE PASSWORD</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>* OLD PASSWORD</name>
                    <codeName>OLDPASSWORDEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">* OLD PASSWORD</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>* NEW PASSWORD</name>
                    <codeName>NEWPASSWORDEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>* CONFIRM NEW PASSWORD</name>
                    <codeName>CONFIRMNEWPASSWORDEditField</codeName>
                    <identification>
                        <properties>
                            <property name="Hint" type="string" isRegExp="false">* CONFIRM NEW PASSWORD</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>UPDATE userSetting</name>
                    <codeName>UPDATEUserSettingButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">UPDATE</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">UPDATE</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>PasswordObj</name>
                    <codeName>PasswordObjUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">12</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Done</name>
                    <codeName>Done</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Done</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Done</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>userNameLabel</name>
                    <codeName>UserNameLabelEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">userNameLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>firstCartElement</name>
                    <codeName>FirstCartElement</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Remove</name>
                    <codeName>RemoveButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Remove</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Remove</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>colorObject</name>
                    <codeName>ColorObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>tabletObj</name>
                    <codeName>TabletObjUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>UPDATE CART</name>
                    <codeName>UPDATECARTButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">UPDATE CART</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">UPDATE CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PasswordLoginEditField</name>
                    <codeName>PasswordLoginEditField</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">passwordLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>passwordLabel</name>
                    <codeName>PasswordLogin</codeName>
                    <description>Password Edit Field in Login page</description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">passwordLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>LOGIN PAGE</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LoggedUserName</name>
                    <codeName>LoggedUserName</codeName>
                    <description>Main Menu Logged user Name text</description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Back</name>
                    <codeName>BackButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Back</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Back</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>AddProductMessage</name>
                    <codeName>AddProductMessage</codeName>
                    <description>Window add product</description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Add product</property>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>AddProductMessageOkButton</name>
                    <codeName>AddProductMessageOkButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Ok</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Ok</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ProductsPageFilter</name>
                    <codeName>ProductsPageFilterButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Filter</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Filter</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>FilterByPriceTab</name>
                    <codeName>FilterByPriceTabLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">BY PRICE</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">BY PRICE</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MobileObjectFilterPriceSlider</name>
                    <codeName>MobileObjectFilterPriceSliderUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">TableCell</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>MobileEdit</name>
                    <codeName>mobileEditEditField</codeName>
                    <description>Quantity field</description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>22</name>
                    <codeName>button</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>userNameTextFieldLabel</name>
                    <codeName>userNameTextFieldLabelEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">userNameTextFieldLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Hint" type="string" isRegExp="false">* USER NAME</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>emailTextFieldLabel</name>
                    <codeName>emailTextFieldLabelEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">emailTextFieldLabel</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Hint" type="string" isRegExp="false">* EMAIL</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>TABLETS</name>
                    <codeName>tABLETSLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">TABLETS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][2][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="Text" type="string" isRegExp="false">TABLETS</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HP ElitePad 1000 G2 Tablet</name>
                    <codeName>hPElitePad1000G2TabletLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">HP ElitePad 1000 G2 Tablet</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">CollectionView[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">HP ElitePad 1000 G2 Tablet</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MobileObject</name>
                    <codeName>mobileObjectUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MobileObject1</name>
                    <codeName>mobileObjectUiObject1</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MobileObject2</name>
                    <codeName>mobileObjectUiObject2</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Change payment Method</name>
                    <codeName>orderBox</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>BtnCheckout</name>
                    <codeName>cHECKOUT</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonCheckOut</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>BtnCheckout1</name>
                    <codeName>cHECKOUT1</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonCheckOut</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>BtnCheckout11</name>
                    <codeName>cHECKOUT11</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">7</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonCheckOut</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Preferred payment was not specified</name>
                    <codeName>preferredPaymentWasNotSpecifiedLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Preferred payment was not specified</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">Preferred payment was not specified</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>MobileObject4</name>
                    <codeName>mobileObjectUiObject3</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SafePayOption</name>
                    <codeName>SafePayOption</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Incorrect user name or password.</name>
                    <codeName>incorrectUserNameOrPasswordLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Incorrect user name or password.</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">Incorrect user name or password.</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>OKBtnPayment</name>
                    <codeName>OKBtnPayment</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">OK</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Application" technology="Mobile">
            <name>Advantage Shopping</name>
            <codeName>advantageShoppingApplication</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.mf.iShopping</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">true</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>APPLY</name>
                    <codeName>aPPLYButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>Server URL</name>
                    <codeName>serverURLEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Hint" type="string" isRegExp="false">Server URL</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Ok</name>
                    <codeName>okButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Ok</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">Ok</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>You are not connected to the internet</name>
                    <codeName>youAreNotConnectedToTheInternetLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">You are not connected to the internet</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">You are not connected to the internet</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>PAY NOW</name>
                    <codeName>pAYNOWButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">PAY NOW</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">PAY NOW</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Application" technology="Mobile">
            <name>Home</name>
            <codeName>homeApplication</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">MC.Home</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>Settings</name>
                    <codeName>settingsButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Settings</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">16</property>
                            <property name="Text" type="string" isRegExp="false">Settings</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Mobile">
                            <name>settingBtn</name>
                            <codeName>settingBtn</codeName>
                            <identification>
                                <properties>
                                    <property name="ClassName" type="string" isRegExp="false">Button</property>
                                    <property name="MobileCenterIndex" type="int" isRegExp="false">16</property>
                                    <property name="AccessibilityId" type="string" isRegExp="false">Setting</property>
                                    <property name="Text" type="string" isRegExp="false">Setting</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
            </children>
        </object>
        <object type="Application" technology="Mobile">
            <name>Settings</name>
            <codeName>settingsApplication</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">MC.Settings</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Label" technology="Mobile">
                    <name>Wi-Fi</name>
                    <codeName>wiFiLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Wi-Fi</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][4][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">Wi-Fi</property>
                        </properties>
                    </identification>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>Wi-Fi</name>
                    <codeName>wiFiToggle</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Wi-Fi</property>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Application" technology="Mobile">
            <name>Websheet</name>
            <codeName>websheetApplication</codeName>
            <description></description>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.apple.WebSheet</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="Mobile">
                    <name>Done</name>
                    <codeName>doneButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Done</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                            <property name="Text" type="string" isRegExp="false">Done</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
