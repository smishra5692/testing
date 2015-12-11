# Android test suite

## Instructions

 - Clone the project

 - Start android studio and  open the cloned project

### Pre requisites for running tests

- Android Device has to be connected while running

- Has to provide package name in app/src/main/java/com/memrise/Base.java file

### Using AndroidStudio or IDE

Run the below test cases which are available in package app/src/androidTest/java/com/memrise traverse to this folder and select test you want to run and right click on it and choose run option

To run all tests present in the suite, traverse to app/src/androidTest/java folder and select the package "com.memrise", right click on it and select run option

###List Of Test Cases Covered

####Login & SignUp Pages

testloginFunctionality

testValidatingLoginPage

testSignupFunctionality

testValidatingSignUpPage

testVerifyLogoutFunctionality

####Home Page

testVerifyStaticTextInHomePage

testVerifyAppMenu

testVerifyDasgboardPage

testVerifyCoursecardInDashboardPage

####Settings Pages

testVerifyProfileSettingsPage

testVerifyChangePasswordFunctionality

####Navigations

testVerifyTopNavigationMenu