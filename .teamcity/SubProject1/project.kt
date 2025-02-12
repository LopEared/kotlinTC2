import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project

object SubProject1 : Project({
    name = "SubProject_1"
    description = "SubProject_1"

    buildType(SubProject1_TestCommands)
    buildType(DependencyConfig_Test)
    buildType(PlugScreenOn)
    buildType(PlugScreenOff)
    buildType(MiddleInChain_Test)

    subProject(SubProjectNest3)

    expectBuildTypesOrder()
    buildTypesOrderIds = arrayListOf(RelativeId("PlugScreenOn"), RelativeId("MiddleInChain_Test"), RelativeId("PlugScreenOff"), RelativeId("DependencyConfig_Test"), RelativeId("SubProject1_TestCommands"))
})