
```java

<?xml version="1.0"?>
<project name="svn-getv2" basedir="." default="pilot">

<taskdef name="ssh" classname="com.sshtools.ant.Ssh" />

<target name="pilot" >

<echo message=" ========= LIVEPUT Start ========= " />

<ssh host="localhost"
username="ibectl"
password="ctl011513"
version="2">
<exec cmd="cd /data/pilot/deploy/live_deploy"/>
<exec cmd="/data/pilot/deploy/live_deploy/live-sta-pilot.sh"/>
</ssh>

<echo message=" ========= LIVEPUT End ========= " />
</target>

</project>
```

- ant -f /data/pilot/deploy/live_deploy/live-sta-pilot.xml


```java
<project name="svn-getv2" basedir="." default="coreput">

<property name="javahomedir" value="/usr/jdk/openjdk8" />
<property name="curr.dir" value="/data/pilot/deploy/live_deploy" />
<property name="dir.svn.put" value="${curr.dir}/liveput/pilot" />

<property name="svnant.repository.user" value="pilot" />
<property name="svnant.repository.passwd" value="1234" />

<tstamp>
<format property="TODAY" pattern="yyyy-MM-dd_HHmm" />
</tstamp>

<typedef resource="org/tigris/subversion/svnant/svnantlib.xml" />

<svnSetting svnkit="true" javahl="false" username="${svnant.repository.user}" password="${svnant.repository.passwd}" id="svn.settings" />

<target name="coreput" >

<copy overwrite="true" file="${from.lib}/${file.jars}" tofile="${from.lib}/${file.jars}" />

<svn refid="svn.settings">
<update revision="HEAD" dir="${dir.svn.put}/sta" />
</svn>

<copy todir="${dir.svn.put}/sta"  overwrite="true">
<fileset dir="${air.lib}">
<include name="oss.fw.*.jar"/>
<exclude name="oss.test.*.jar"/>
</fileset>
</copy>

<svn refid="svn.settings">
<add dir="${dir.svn.put}/sta" force="true" recurse="true" />
<commit message=" Auto Commit Topas Server Build Script ${TODAY} " dir="${dir.svn.put}/sta"/>
</svn>


</target>
</project>
```

### branch 

```java
echo "${MODULE_NAME}-${RELEASE_DATE} Release Directory를 생성합니다.";
svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} \
--message "${MODULE_NAME}-${RELEASE_DATE} Release Directory를 생성합니다." \
mkdir svn://localsvn.pilot.co.kr/pssSite_Pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE};

echo "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Static Source에 대한 Release Branch를 생성합니다.";
svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} \
--message "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Static Source에 대한 Release Branch를 생성합니다." \
copy svn://localsvn.pilot.co.kr/pssSite_Pilot/trunk/Pilot_VOFStatic \
svn://localsvn.pilot.co.kr/pssSite_Pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE}/iq3_ibeVOFStatic;

echo "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Dynamic Source에 대한 Release Branch를 생성합니다.";
svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} \
--message "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Dynamic Source에 대한 Release Branch를 생성합니다." \
copy svn://localsvn.pilot.co.kr/pssSite_Pilot/trunk/Pilot_VOFDynamic \
svn://localsvn.pilot.co.kr/pssSite_Pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE}/Pilot_VOFDynamic;

```