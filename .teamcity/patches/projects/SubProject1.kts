package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the project with id = 'SubProject1'
accordingly, and delete the patch script.
*/
changeProject(RelativeId("SubProject1")) {
    expectBuildTypesOrder()
    buildTypesOrderIds = arrayListOf(RelativeId("DependencyConfig_Test"), RelativeId("PlugScreenOn"), RelativeId("MiddleInChain_Test"), RelativeId("PlugScreenOff"), RelativeId("SubProject1_TestCommands"))
}
