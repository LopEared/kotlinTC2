import _self.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.ui.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR


object MiddleInChain_Test : BuildType({
    name = "BuildConfig MiddleInChain"
    description = "Dependency Config Test"

    type = REGULAR
    enablePersonalBuilds = false
    maxRunningBuilds = 1

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        MiddleInChainFunc()
    }

    requirements {
        equals("teamcity.agent.name", "ip_172.17.0.1")
    }
})

private fun BuildSteps.MiddleInChainFunc() {
    script {
        name = "Middle In Chain Func"
        id = "MiddleInChainFunc_ID"
        workingDir = "/"
        scriptContent = """
                #!/bin/bash
                echo "TEST NOTE FOR FILE MIDDLE IN CHAIN: " > %build.number%_middle_in_chain.txt
                sleep 10
                """.trimIndent().replace("$!", "$")
    }
}