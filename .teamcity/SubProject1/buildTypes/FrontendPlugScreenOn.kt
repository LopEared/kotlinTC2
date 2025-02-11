package Deploy.STAGE.buildTypes

import Deploy.STAGE.buildTypes.StageCopyCommon.sshExec
import jetbrains.buildServer.configs.kotlin.BuildSteps
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.BuildTypeSettings.Type.REGULAR

object FrontendPlugScreenOn : BuildType({
    name = "FrontendPlugScreenOn"

    type = REGULAR

    vcs {
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        frontendPlugScreenUP("30222")
        frontendPlugScreenUP("36022")
    }
})

private fun BuildSteps.frontendPlugScreenUP(port: String) {
    step {
        name = "Service Plugs UP"
        sshExec(
            port, """
                    sudo bash -c "cd /etc/nginx/html/ && mv maintenance-stage.html2 maintenance-stage.html || exit 1"
                """
        )
    }
}
