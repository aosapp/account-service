<?xml version="1.0" encoding="utf-8" standalone="yes"?>
<applicationModel>
    <name>Application Model</name>
    <className>AdvantageAndroidApp</className>
    <objects>
        <object type="Application" technology="Mobile">
            <name>Advantage Shopping</name>
            <codeName>AdvantageShoppingApplication</codeName>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">com.Advantage.aShopping</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">true</property>
                </properties>
            </identification>
            <children>
                <object type="Label" technology="Mobile">
                    <name>LAPTOPS</name>
                    <codeName>LAPTOPSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LAPTOPS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuLaptops</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">12</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SPEAKERS</name>
                    <codeName>SPEAKERSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SPEAKERS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuSpeakers</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">13</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>TABLETS</name>
                    <codeName>TABLETSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">TABLETS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuTablets</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>MICE</name>
                    <codeName>MICELabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">MICE</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuMice</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">15</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HEADPHONES</name>
                    <codeName>HEADPHONESLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HEADPHONES</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuHeadphones</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">16</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Menu</name>
                    <codeName>MainMenu</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewMenu</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>UserName Edit</name>
                    <codeName>UserNameEdit</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>Pass Edit</name>
                    <codeName>PassEdit</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>LOGIN button</name>
                    <codeName>LOGINButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonLogin</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Don't have an account?</name>
                    <codeName>DonTHaveAnAccountLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Don't have an account?</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewDontHaveAnAccount</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SIGN OUT</name>
                    <codeName>SIGNOUTLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SIGN OUT</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuSignOut</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>YES</name>
                    <codeName>YESButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">YES</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">button2</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>signOut</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LOGIN</name>
                    <codeName>Login</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">LOGIN</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuUser</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Cart access</name>
                    <codeName>CartAccess</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Cart access</property>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewCart</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>laptop item</name>
                    <codeName>LaptopItem</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][2][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewProduct</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>ADD TO CART</name>
                    <codeName>ADDTOCARTButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADD TO CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonProductAddToCart</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>linearLayoutProductQuant</name>
                    <codeName>ProductQuantity</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">linearLayoutProductQuantity</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>linearLayoutProductColor</name>
                    <codeName>ProductColor</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">linearLayoutProductColors</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">25</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>linearLayoutProductDetai</name>
                    <codeName>ProductDetail</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">linearLayoutProductDetails</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">26</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>laptop item2</name>
                    <codeName>LaptopItem2</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][7][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewProduct</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>color object</name>
                    <codeName>colorObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">33</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>APPLY change</name>
                    <codeName>APPLYChangeLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewApply</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>CHECKOUT</name>
                    <codeName>CHECKOUT</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonCheckOut</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="View" technology="Mobile">
                    <name>scrollViewCartProducts</name>
                    <codeName>ScrollViewCartProducts</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Scroller</property>
                            <property name="ResourceId" type="string" isRegExp="false">scrollViewCartProducts</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>PAY NOW</name>
                    <codeName>PAYNOWButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">PAY NOW</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonPayNow</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="CheckBox" technology="Mobile">
                    <name>Save SafePay credentials</name>
                    <codeName>SaveSafePayCredentialsCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">CheckBox</property>
                            <property name="ResourceId" type="string" isRegExp="false">checkBoxPrefferedShipping</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>userNameSignUp</name>
                    <codeName>UserNameSignUp</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>REGISTER</name>
                    <codeName>REGISTERButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">REGISTER</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonRegister</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageView Safe Pay</name>
                    <codeName>ImageViewSafePay</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewSafePage</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewMasterCredit</name>
                    <codeName>ImageViewMasterCredit</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewMasterCredit</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>safePay Userfield</name>
                    <codeName>SafePayUserfieldEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>safePay pass field</name>
                    <codeName>SafePayPassFieldEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>payment details</name>
                    <codeName>PaymentDetails</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>safepay</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HOME</name>
                    <codeName>HOMELabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Advantage</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewAdvantage</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Quantity</name>
                    <codeName>QuantityLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">1</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewProductQuantity</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>UPDATE PRODUCT</name>
                    <codeName>UPDATEPRODUCTButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">UPDATE PRODUCT</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonProductAddToCart</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Verify receipt window</name>
                    <codeName>VerifyReceiptWindowUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">13</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>CartQuantity</name>
                    <codeName>CartQuantityLabel</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewCartLength</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>firstCartItem</name>
                    <codeName>FirstCartItem</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">linearLayoutFront</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">38</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>CartRemove</name>
                    <codeName>CartRemove</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">relativeLayoutCartRemove</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">45</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>cart</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>AccountDetails</name>
                    <codeName>AccountDetails</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewAccountDetails</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Tablet Item</name>
                    <codeName>tabletItem</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][0][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewProduct</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>textViewProductQuantity</name>
                    <codeName>ProductQuantityEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewProductQuantity</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>quantity</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>Login O</name>
                    <codeName>LoginObj</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">36</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Change password</name>
                    <codeName>ChangePasswordLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Change password</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewChange_password</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>oldPass</name>
                    <codeName>OldPassEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>NewPass</name>
                    <codeName>NewPassEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>password</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ConfirmNewPass</name>
                    <codeName>ConfirmNewPassEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>password</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>UPDATEAccount</name>
                    <codeName>UPDATEAccountButton</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">UPDATE</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonUpdate</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>password</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewFilter</name>
                    <codeName>ImageViewFilter</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewFilter</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>BY MANUFACTURER</name>
                    <codeName>BYMANUFACTURERLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">BY MANUFACTURER</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][2][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewFilterCategoryTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HP</name>
                    <codeName>HPLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HP</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][4][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewFilterCategoryTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SOLD</name>
                    <codeName>SOLDout</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SOLD</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][1][0]</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>laptopFilter2</name>
                    <codeName>LaptopFilter2UiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][5][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewProduct</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>EditShipping</name>
                    <codeName>EditShippingUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CVVMasterCredit</name>
                    <codeName>CVVMasterCreditEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>monthMasterCredit</name>
                    <codeName>MonthMasterCreditLabel</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMonths</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>YEARMasterCredit</name>
                    <codeName>YEARMasterCreditLabel</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewYears</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">13</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>confirmPassSignUp</name>
                    <codeName>ConfirmPassSignUp</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>EmailSignUp</name>
                    <codeName>EmailSignUp</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>PasswordSignUp</name>
                    <codeName>PasswordSignUp</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SignUp</name>
                    <codeName>SignUp</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>HOME1</name>
                    <codeName>HOME</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">HOME</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuHome</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">7</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MainMenu</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SETTINGS</name>
                    <codeName>SETTINGSLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">SETTINGS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuSettings</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>editTextServer</name>
                    <codeName>EditTextServer</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="ResourceId" type="string" isRegExp="false">editTextServer</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>Connect</name>
                    <codeName>ConnectButton</codeName>
                    <description>Settings Page, Connect Button</description>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Connect</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonConnect</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>contentPanel</name>
                    <codeName>ContentPanelConnection</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">contentPanel</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>setting</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SignUPtUiObject</name>
                    <codeName>SignUPtUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>buttonPanelSetting</name>
                    <codeName>ButtonPanelSettingUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonPanel</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>setting</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>SignUpObject</name>
                    <codeName>SignUpObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">12</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>ChangePasswordObject</name>
                    <codeName>ChangePasswordObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">26</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>password</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>laptopitemWin10</name>
                    <codeName>LaptopitemWin10</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="Container" type="string" isRegExp="false">Table[0][3][0]</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewProduct</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CardHolderMasterCredit</name>
                    <codeName>CardHolderMasterCreditEditField</codeName>
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
                <object type="UiObject" technology="Mobile">
                    <name>AdvantageObject</name>
                    <codeName>AdvantageObjectUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">RelativeLayoutMenu</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">14</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ZIPmyAccaunt</name>
                    <codeName>ZIPmyAccauntEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">7</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CardNumderMasterCreditEditField</name>
                    <codeName>CardNumderMasterCreditEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="CheckBox" technology="Mobile">
                    <name>Save MasterCredit creden</name>
                    <codeName>SaveMasterCreditCredenCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">CheckBox</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="IsCheckable" type="boolean" isRegExp="false">true</property>
                            <property name="ResourceId" type="string" isRegExp="false">checkBoxPrefferedShipping</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>MasterCredit</tag>
                    </tags>
                </object>
                <object type="CheckBox" technology="Mobile">
                    <name>Use this address as pref</name>
                    <codeName>ShippingCheckBox</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">CheckBox</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="IsCheckable" type="boolean" isRegExp="false">true</property>
                            <property name="ResourceId" type="string" isRegExp="false">checkBoxPrefferedShipping</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>shipping</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ZIPshippingDetaild</name>
                    <codeName>ZIPshippingDetaildEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">6</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>shipping</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>StateSignUp</name>
                    <codeName>StateSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>AddressSignUp</name>
                    <codeName>AddressSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>CitySignUp</name>
                    <codeName>CitySignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ZIPSignUp</name>
                    <codeName>ZIPSignUpEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="Container" type="string" isRegExp="false"></property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>BY OPERATING SYSTEM</name>
                    <codeName>BYOPERATINGSYSTEMLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">BY OPERATING SYSTEM</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewFilterCategoryTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Windows 10</name>
                    <codeName>Windows10Label</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Windows 10</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewFilterCategoryTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>filter</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>CART</name>
                    <codeName>CARTLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">CART</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuCart</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">17</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewCloseDialog</name>
                    <codeName>CloseDialog</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewCloseDialog</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>linearLayoutLogin</name>
                    <codeName>LinearLayoutLogin</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">linearLayoutLogin</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">28</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>login</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Server not reachable</name>
                    <codeName>ServerNotReachableLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Server not reachable</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">alertTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Don't have an account?1</name>
                    <codeName>DonTHaveAnAccount</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Don't have an account?</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewDontHaveAnAccount</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">7</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>SignUp</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>You are not connected to</name>
                    <codeName>YouAreNotConnectedToLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">You are not connected to the internet</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">alertTitle</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>offline</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Note offline mod</name>
                    <codeName>NoteOfflineModLabel</codeName>
                    <description>Message appears in Settings Page after click on Apply button in Offline Mode</description>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">You are working in offline mode. Data is not retained for future use</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">message</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>offline</tag>
                    </tags>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>warningMessage</name>
                    <codeName>WarningMessageUiObject</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">View</property>
                            <property name="ResourceId" type="string" isRegExp="false">warningMessage</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">10</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>offline</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>LoggedUserName</name>
                    <codeName>LoggedUserName</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuUser</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SignedInUserName</name>
                    <codeName>SignedInUserName</codeName>
                    <description>In Menu signed In user name</description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewMenuUser</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">8</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>MobileEdit</name>
                    <codeName>ShippingDetailsFirstNameEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>ShippingDetailsAddressTextField</name>
                    <codeName>ShippingDetailsAddressTextFieldLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">ADDRESS</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>ShippingDetailsAddressTextField</name>
                    <codeName>ShippingDetailsAddressTextField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Incorrect user name or p1</name>
                    <codeName>IncorrectUserNameOrPLabel1</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Incorrect user name or password.</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">textViewFailed</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>MobileEdit1</name>
                    <codeName>mobileEditEditField</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Keyboard</property>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">11</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>You are not connected to internet</name>
                    <codeName>youAreNotConnectedToInternetLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">You are not connected to internet</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="Text" type="string" isRegExp="false">You are not connected to internet</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>OK</name>
                    <codeName>oKButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/button1</property>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                        </properties>
                    </identification>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>Enable fingerprint authentication</name>
                    <codeName>enableFingerprintAuthenticationToggle</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">fingerprintSwitch</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>APPLY</name>
                    <codeName>ApplyButton</codeName>
                    <description>Settings Page, Apply button in Offline Mode</description>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">APPLY</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">buttonConnect</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>OK1</name>
                    <codeName>oKButtonServer</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/button1</property>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Connected successfully</name>
                    <codeName>connectedSuccessfullyLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="ResourceId" type="string" isRegExp="false">message</property>
                            <property name="Text" type="string" isRegExp="false">Connected successfully</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>OK2</name>
                    <codeName>oKButton1</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">2</property>
                            <property name="ResourceId" type="string" isRegExp="false">button1</property>
                            <property name="Text" type="string" isRegExp="false">OK</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewCategory</name>
                    <codeName>imageViewCategoryUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                            <property name="ResourceId" type="string" isRegExp="false">imageViewCategory</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
        <object type="Application" technology="Mobile">
            <name>Settings</name>
            <codeName>SettingsApplication</codeName>
            <identification>
                <properties>
                    <property name="Identifier" type="string" isRegExp="false">MC.Settings</property>
                    <property name="IsPackaged" type="boolean" isRegExp="false">false</property>
                </properties>
            </identification>
            <children>
                <object type="UiObject" technology="Mobile">
                    <name>com.android.settings:id/</name>
                    <codeName>AirplaneMode</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/icon</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">9</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>offline</tag>
                    </tags>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>airplaine</name>
                    <codeName>AirplaneToggleONSwitch</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/switch_widget</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>offline</tag>
                    </tags>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SettingsConnections</name>
                    <codeName>SettingsConnections</codeName>
                    <description>Settings -&gt; Connections</description>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Connections</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/title</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Settings</tag>
                    </tags>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>ConnectionsAirplaneModeToggle</name>
                    <codeName>ConnectionsAirplaneModeToggle</codeName>
                    <description>Settings -&gt; Connections -&gt; Airplane Mode  toggle</description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/switch_widget</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">3</property>
                        </properties>
                    </identification>
                    <tags>
                        <tag>Settings</tag>
                    </tags>
                </object>
                <object type="Button" technology="Mobile">
                    <name>SettingsSearchButton</name>
                    <codeName>SettingsSearchButton</codeName>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Search</property>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/search</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="EditField" technology="Mobile">
                    <name>SettingsSearchTextEditField</name>
                    <codeName>SettingsSearchTextEditField</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Input</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/search_src_text</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>AirplaneModeSettingsButton</name>
                    <codeName>AirplaneModeSettingsButtonLabel</codeName>
                    <identification>
                        <properties>
                            <property name="Text" type="string" isRegExp="false">Airplane mode</property>
                            <property name="AccessibilityId" type="string" isRegExp="false">Airplane mode</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/title</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>AirplaneToggleOffSwitch</name>
                    <codeName>AirplaneToggleOffSwitchToggle</codeName>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/switch_widget</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Wi-Fi</name>
                    <codeName>wiFiLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="AccessibilityId" type="string" isRegExp="false">Wi-Fi</property>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.android.settings:id/title</property>
                            <property name="Text" type="string" isRegExp="false">Wi-Fi</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>SFT-Internet</name>
                    <codeName>sFTInternetLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/title</property>
                            <property name="Text" type="string" isRegExp="false">SFT-Internet</property>
                        </properties>
                    </identification>
                </object>
                <object type="Label" technology="Mobile">
                    <name>Connected</name>
                    <codeName>connectedLabel</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Label</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">5</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/summary</property>
                            <property name="Text" type="string" isRegExp="false">Connected</property>
                        </properties>
                    </identification>
                </object>
                <object type="Button" technology="Mobile">
                    <name>FORGET</name>
                    <codeName>fORGETButton</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Button</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">1</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/button1</property>
                            <property name="Text" type="string" isRegExp="false">FORGET</property>
                        </properties>
                    </identification>
                </object>
                <object type="Toggle" technology="Mobile">
                    <name>ON</name>
                    <codeName>oNToggle</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">Switch</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">0</property>
                            <property name="ResourceId" type="string" isRegExp="false">android:id/switch_widget</property>
                        </properties>
                    </identification>
                </object>
                <object type="UiObject" technology="Mobile">
                    <name>imageViewProduct</name>
                    <codeName>imageViewProductUiObject</codeName>
                    <description></description>
                    <identification>
                        <properties>
                            <property name="ClassName" type="string" isRegExp="false">ImageView</property>
                            <property name="MobileCenterIndex" type="int" isRegExp="false">4</property>
                            <property name="ResourceId" type="string" isRegExp="false">com.Advantage.aShopping:id/imageViewProduct</property>
                        </properties>
                    </identification>
                </object>
            </children>
        </object>
    </objects>
</applicationModel>
