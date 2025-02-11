import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project
import Deploy.STAGE.buildTypes.FrontendPlugScreenOff
import Deploy.STAGE.buildTypes.FrontendPlugScreenOn


object SubProject1 : Project({
    name = "SubProject_1"
    description = "SubProject_1"

    buildType(SubProject1_TestCommands)
    buildType(DependencyConfig_Test)

    buildType(FrontendPlugScreenOff)
    buildType(FrontendPlugScreenOn)

    subProject(SubProjectNest3)
})