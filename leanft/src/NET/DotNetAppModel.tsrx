<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>DotNetAppModel</className>
    <objects>
        <object type="Window" technology="WPF">
            <name>Advantage Shop Administrator</name>
            <codeName>AdvantageShopAdministrator</codeName>
            <identification>
                <properties>
                    <property name="ObjectName" type="string" isRegExp="false">AOS Managment Console</property>
                    <property name="FullType" type="string" isRegExp="false">window</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                </properties>
            </identification>
            <children>
                <object type="List" technology="WPF">
                    <name>TopBar</name>
                    <codeName>TopBarList</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">list view</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>0</index>
                    </identification>
                    <tags>
                        <tag>top</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Password</name>
                    <codeName>PasswordEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">Password_Box</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Server</name>
                    <codeName>ServerEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="CheckBox" technology="WPF">
                    <name>Remember me</name>
                    <codeName>RememberMeCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Remember me</property>
                            <property name="ObjectName" type="string" isRegExp="false">Remember me</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>SIGN IN</name>
                    <codeName>SIGNINButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SIGN IN</property>
                            <property name="ObjectName" type="string" isRegExp="false">SIGN IN</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="WPF">
                    <name>admin</name>
                    <codeName>AdminUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;</property>
                            <property name="FullType" type="string" isRegExp="false">object</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>Logout</name>
                    <codeName>LogoutButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Logout</property>
                            <property name="ObjectName" type="string" isRegExp="false">Logout</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="WPF">
                    <name>PRODUCTS </name>
                    <codeName>PRODUCTSUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">object</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>main</tag>
                    </tags>
                </object>
                <object type="List" technology="WPF">
                    <name>SideList</name>
                    <codeName>SideList</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">list view</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                    </identification>
                </object>
                <object type="Table" technology="WPF">
                    <name>ProductsDataGrid</name>
                    <codeName>ProductsDataGridTable</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">ProductsDataGrid</property>
                        </properties>
                    </identification>
                </object>
                <object type="TabStrip" technology="WPF">
                    <name>ProductsWpfTabStrip</name>
                    <codeName>ProductsWpfTabStrip</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">tab</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>ADD COLORS</name>
                    <codeName>ADDCOLORSButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD COLORS</property>
                            <property name="ObjectName" type="string" isRegExp="false">addColorButton</property>
                        </properties>
                    </identification>
                </object>
                <object type="CheckBox" technology="WPF">
                    <name>YelloeCheckBox</name>
                    <codeName>YelloeCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="ParentText" type="string" isRegExp="false"></property>
                            <property name="Text" type="string" isRegExp="false"></property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">check box</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>5</index>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>SAVE</name>
                    <codeName>SAVEButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SAVE</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
                <object type="Table" technology="WPF">
                    <name>ProductsColorsGrid</name>
                    <codeName>ProductsColorsGridTable</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">ProductsColorsGrid</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="WPF">
                    <name>ADD USER</name>
                    <codeName>ADDUSERButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD USER</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>User Name_UserManage</name>
                    <codeName>UserNameUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>1</index>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Email_UserManage</name>
                    <codeName>EmailUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>2</index>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Confirm password_UserManage</name>
                    <codeName>ConfirmPasswordUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">PasswordConfirm_Box</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>First Name_UserManage</name>
                    <codeName>FirstNameUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>3</index>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Last Name_UserManage</name>
                    <codeName>LastNameUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>4</index>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>OK_UserManage</name>
                    <codeName>OKUserManageButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                            <property name="ObjectName" type="string" isRegExp="false">btnCreateNewUserOk</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Password_UserManage</name>
                    <codeName>PasswordUserManageEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">Password_Box</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="WPF">
                    <name>ContentControl</name>
                    <codeName>UsersControlUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">ContentControl</property>
                            <property name="FullNamePath" type="string" isRegExp="false">ContentControl;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">object</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>UserManage</tag>
                    </tags>
                </object>
                <object type="Table" technology="WPF">
                    <name>UsersTypesGrid</name>
                    <codeName>UsersTypesGridTable</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">UsersTypesGrid</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="WPF">
                    <name>Search</name>
                    <codeName>SearchEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="WPF">
                    <name>WEIGHT</name>
                    <codeName>WEIGHTEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>3</index>
                    </identification>
                </object>
                <object type="Dialog" technology="Standard Windows">
                    <name>create user failed</name>
                    <codeName>createUserFailedDialog</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="IsChildWindow" type="boolean" isRegExp="false">false</property>
                            <property name="IsOwnedWindow" type="boolean" isRegExp="false">true</property>
                            <property name="NativeClass" type="string" isRegExp="false">#32770</property>
                            <property name="Text" type="string" isRegExp="false">create user failed</property>
                        </properties>
                    </identification>
                    <children>
                        <object type="Button" technology="Standard Windows">
                            <name>OK</name>
                            <codeName>oKButton</codeName>
                            <description></description>
                            <identification>
                                <properties>
                                    <property name="NativeClass" type="string" isRegExp="false">Button</property>
                                    <property name="Text" type="string" isRegExp="false">OK</property>
                                </properties>
                            </identification>
                        </object>
                    </children>
                </object>
                <object type="Button" technology="WPF">
                    <name>CANCEL</name>
                    <codeName>cANCELButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="Text" type="string" isRegExp="false">CANCEL</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="WPF">
                    <name>User Name</name>
                    <codeName>userNameEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="FullNamePath" type="string" isRegExp="false">;;;</property>
                            <property name="FullType" type="string" isRegExp="false">edit</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                            <property name="WindowTitleRegExp" type="string" isRegExp="false">AOS Managment Console</property>
                        </properties>
                        <index>0</index>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Window" technology="WPF">
            <name>Reset password for 'Dot.NetUser'</name>
            <codeName>ResetPasswordForDotNetUserWindow</codeName>
            <identification>
                <properties>
                    <property name="ObjectName" type="string" isRegExp="false">resPassWin</property>
                    <property name="FullType" type="string" isRegExp="false">window</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false"></property>
                </properties>
            </identification>
            <children>
                <object type="EditField" technology="WPF">
                    <name>newPass</name>
                    <codeName>NewPassEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">pasBox</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="WPF">
                    <name>rePass</name>
                    <codeName>RePassEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ObjectName" type="string" isRegExp="false">pasRetBox</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
                <object type="Button" technology="WPF">
                    <name>OK_Password</name>
                    <codeName>OKPasswordButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                            <property name="ObjectName" type="string" isRegExp="false">btnResetPasswordOk</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Password</tag>
                    </tags>
                </object>
            </children>
        </object>
        <object type="Window" technology="WPF">
            <name>Delete User Confirmation</name>
            <codeName>DeleteUserConfirmationWindow</codeName>
            <identification>
                <properties>
                    <property name="ObjectName" type="string" isRegExp="false">deleteUserWindow</property>
                    <property name="FullType" type="string" isRegExp="false">window</property>
                    <property name="WindowTitleRegExp" type="string" isRegExp="false">Delete User Confirmation</property>
                </properties>
            </identification>
            <children>
                <object type="Button" technology="WPF">
                    <name>OK_delete</name>
                    <codeName>OKDeleteButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                            <property name="ObjectName" type="string" isRegExp="false"></property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
