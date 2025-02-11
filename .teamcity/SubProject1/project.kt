import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project

object SubProject1 : Project({
    name = "SubProject_1"
    description = "SubProject_1"

    buildType(SubProject1_TestCommands)
    buildType(DependencyConfig_Test)

    subProject(SubProject1.SubProject_Nest2)
})