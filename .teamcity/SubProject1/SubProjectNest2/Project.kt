import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.Project

object SubProjectNest3 : Project({
    id("SubProject1_SubProjectNest3")
    name = "SubProject_Nest3_Project"
    description = "Description_SubProject_Nest3"

    buildType(SubProject_Nest3_buildConfig)
})
