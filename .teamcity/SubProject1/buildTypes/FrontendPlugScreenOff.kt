import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOff : BuildType({
    name = "FrontendPlugScreenOff"

    type = REGULAR

    vcs {
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenDown()
    }
})

private fun BuildSteps.frontendPlugScreenDown(port: String) {
    step {
        script {
            name = "Quasi Turn On"
            id = "QuasiTurnOn"
            workingDir = "/"
            scriptContent = """
                    #!/bin/bash
                    mv maintenance-stage.html maintenance-stage.html2 || exit 1
                    """.trimIndent()
        }
    }
}
