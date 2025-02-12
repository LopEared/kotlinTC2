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
        add {
            checkbox("TestCheckBox", "", label = "TEST FROM LEFT SIDE", description = "Test Check Box for condition", display = ParameterDisplay.PROMPT,
                      checked = "true")
        }
    }
}
