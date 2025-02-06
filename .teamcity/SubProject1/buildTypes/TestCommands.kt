object SubProject1_TestCommands : BuildType({
    name = "TestCommands"
    description = "TestCommands"

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