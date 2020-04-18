● EBS 볼륨 추가방법 예시 (root 사용자에서 진행..)
# lsblk
# df -Th
# file -s /dev/nvme1n1
# mkfs -t xfs /dev/nvme1n1
# mount /dev/nvme1n1 /app
# blkid
# vim /etc/fstab
# umount /app
# mount -a

참고url: Amazon EBS 볼륨을 Linux에서 사용할 수 있도록 만들기
https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/ebs-using-volumes.html
