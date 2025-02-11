import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project
import SubProject1.SubProjectNest2.buildTypes.TestDependcyFromSubProject

object SubProjectNest2 : Project({
    id("SubProject1_SubProjectNest2")
    name = "SubProject_Nest2_Project"
    description = "Test Dependcies from subproject"

    buildType(SubProject_Nest2_buildConfig)
})

object SubProjectNest3 : Project({
    id("SubProject1_SubProjectNest3")
    name = "SubProject_Nest3_Project"
    description = "Description_SubProject_Nest3"
})
