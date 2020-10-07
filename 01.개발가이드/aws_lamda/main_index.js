const SSH = require('node-ssh');
const AWS = require('aws-sdk');
const ec2 = new AWS.EC2();
const pemfile = process.env.pemfile;

AWS.config.region = 'ap-northeast-2';

exports.handler = async (event) => {

    const instanceId = event['detail']['EC2InstanceId'];
    const params = { InstanceIds: [instanceId] };
    // TODO implement
    console.log("AutoScalingEvent()");

    console.log("params:\n" + JSON.stringify(params, null, 4));

    const privateIp = await getPrivateIp(params);
    let connInfo = await getSSHInfo(privateIp);

    try {
        await connInfo.putFile('./AgentDeploymentScript.sh', './AgentDeploymentScript.sh');
        console.log("file transfer success...!");
    } catch (err) {
        console.log("file transfer failed... ");
        console.log(err);
    }

    try {
        await connInfo.execCommand("sed -i -e 's/\r$//' ./AgentDeploymentScript.sh");
        console.log("file type change to Unix");
    } catch (err) {
        console.log("file type change failed... ");
        console.log(err);
    }

    try {
        console.log('sudo chmod 700 ./AgentDeploymentScript.sh');
        let result = await connInfo.execCommand('sudo chmod 700 ./AgentDeploymentScript.sh', { options: { pty: true } });
        console.log('result: ' + result.stdout);
    } catch (err) {
        console.log('error: ' + err);
    }

    try {
        console.log('sudo ./AgentDeploymentScript.sh');
        let result = await connInfo.execCommand('sudo ./AgentDeploymentScript.sh', { options: { pty: true } });
        console.log('result: ' + result.stdout);
    } catch (err) {
        console.log('error: ' + err);
    }

    return 200;
};

const getPrivateIp = async (params) => {
    try {
        let data = await ec2.describeInstances(params).promise();
        console.log("call describeInstances...!");

        let privateIp = data['Reservations'][0]['Instances'][0]['PrivateIpAddress'];
        console.log("private IP : " + privateIp);
        return privateIp;
    } catch (err) {
        console.log(err, err.stack); // an error occurred
        return 0;
    }
};

const getSSHInfo = async (privateIp) => {
    let conn;
    const ssh = new SSH();
    try {
        conn = await ssh.connect({
            host: privateIp,
            username: 'ec2-user',
            privateKey: pemfile
        });
        console.log("connection success...!");
    } catch (err) {
        console.log("connection failed... ");
        console.log("retry connection one more");

        conn = await ssh.connect({
            host: privateIp,
            username: 'ec2-user',
            privateKey: pemfile
        });
    }

    return conn;
};

// const conntectionSSH = (privateIp) => {
//     const ssh = new SSH();
//     conn = await ssh.connect({
//         host: privateIp,
//         username: 'ec2-user',
//         privateKey: pemfile
//     });
// }