import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOff : BuildType({
    name = "FrontendPlugScreenOff"

    type = REGULAR

    vcs {
        root(DslContext.settingsRoot)
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenDown()
    }
})

private fun BuildSteps.frontendPlugScreenDown() {
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
