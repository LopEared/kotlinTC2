import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project

object SubProjectNest2 : Project({
    name = "SubProject_Nest2_Project"
    description = "Test Dependcies from subproject"

    buildType(SubProject_Nest2_buildConfig)
})