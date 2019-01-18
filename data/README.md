**Command Line**

    mvn sql:execute@db-drop
    mvn sql:execute@db-seed
    
**Maven Settings**
    <!-- put this somewhere in directory below -->
    <!-- <USERHOME>/.m2/settings.xml -->
    <servers>
        <server>
            <id>postgres-dev</id>
            <username>postgres</username>
            <password>abc123</password>
        </server>
    </servers>
