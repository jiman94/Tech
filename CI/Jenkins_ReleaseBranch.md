# Release Branch

### Build > Execute shell 
* Command 
> MODULE_NAME

> RELEASE_DATE

> SVN_USERNAME

```bash 
echo "${MODULE_NAME}-${RELEASE_DATE} Release Directory를 생성합니다.";

svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} --message "${MODULE_NAME}-${RELEASE_DATE} Release Directory를 생성합니다." \

mkdir svn://pilot.도메인.com/pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE};

echo "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Static Source에 대한 Release Branch를 생성합니다.";

svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} \
--message "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Static Source에 대한 Release Branch를 생성합니다." \

copy svn://pilot.도메인.com/pilot/trunk/pilot_pilotStatic \
svn://pilot.도메인.com/pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE}/pilot_pilotStatic;

echo "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Dynamic Source에 대한 Release Branch를 생성합니다.";
svn --no-auth-cache --non-interactive --username ${SVN_USERNAME} --password ${SVN_PASSWD} \
--message "${MODULE_NAME}-${RELEASE_DATE} Release를 위한 Dynamic Source에 대한 Release Branch를 생성합니다." \

copy svn://pilot.도메인.com/pilot/trunk/pilot_pilotDynamic \
svn://pilot.도메인.com/pilot/branches/releases/${MODULE_NAME}-${RELEASE_DATE}/pilot_pilotDynamic;
```

```
echo "===========================================================";
echo "빌드에 대한 Meta 정보 파일을 생성합니다.";
echo "===========================================================";
cd ${WORKSPACE}/dist;
echo "BUILD TAG: ${BUILD_TAG}" > build.meta
echo "BUILD ID: ${BUILD_ID}" >> build.meta
echo "SVN RELEASE: ${SVN_RELEASE}" >> build.meta
echo "SVN REVISION: ${SVN_REVISION}" >> build.meta
cat build.meta
``
