import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object SubProject_Nest2 : Project({
    name = "SubProject_Nest2_Project"
    description = "Test Dependcies from subproject"

    buildType(SubProject_Nest2_buildConfig)
})