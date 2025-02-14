package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'PlugScreenOff'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("PlugScreenOff")) {
    params {
        expect {
            checkbox("TestCheckBox", "true", label = "Screen plug during process", description = "Will put up a screen plug before deployment and removed it after deployment.", display = ParameterDisplay.PROMPT,
                      checked = "true", unchecked = "false")
        }
        update {
            checkbox("TestCheckBox", "true", label = "Screen plug during process", description = "Will put up a screen plug before deployment and removed it after deployment.", display = ParameterDisplay.HIDDEN, readOnly = true,
                      checked = "true", unchecked = "false")
        }
    }
}
