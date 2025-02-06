import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object SubProject1_TestCommands : BuildType({
    name = "TestCommands"
    description = "TestCommands"

    params {
        text("inputedbranch", "", label = "Confirm branch name:", display = ParameterDisplay.PROMPT, allowEmpty = false)
        password("testPass", "credentialsJSON:3d93ad41-aacd-4523-8208-9f4cb2ca9531", label = "TestLabelCheckout", description = "TestDescription")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "SomeCommands"
            id = "SomeCommands"
            workingDir = "/"
            scriptContent = """echo "New Nontes into files" >  %build.number%_%teamcity.build.branch%_testFile.txt"""
        }
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})