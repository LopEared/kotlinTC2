import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object SubProject1 : Project({
    name = "SubProject_1"
    description = "SubProject_1"

    buildType(SubProject1_TestCommands)
})