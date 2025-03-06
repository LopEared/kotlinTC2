package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'SubProject1_TestCommands'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("SubProject1_TestCommands")) {
    params {
        expect {
            password("password", "", label = "Password", description = "Input password to start build", display = ParameterDisplay.PROMPT)
        }
        update {
            password("password", "credentialsJSON:88b29559-f978-4cc6-a1ab-34d1a44d9959", label = "Password", description = "Input password to start build", display = ParameterDisplay.PROMPT)
        }
    }
}
