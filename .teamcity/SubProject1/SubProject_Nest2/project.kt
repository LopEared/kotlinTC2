import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import SubProject1.SubProject_Nest2.buildTypes.TestDependcyFromSubProject

object SubProject_Nest2 : Project({
    name = "SubProject_Nest2_Project"
    description = "Test Dependcies from subproject"

    buildType(SubProject_Nest2_buildConfig)
})