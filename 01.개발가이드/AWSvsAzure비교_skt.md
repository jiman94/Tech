
<h3 id="data-orchestration--etl"><span data-ttu-id="24fb8-179">데이터 오케스트레이션/ETL</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-180">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-181">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-182">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><span data-ttu-id="24fb8-183"><a href="https://aws.amazon.com/datapipeline/" data-linktype="external">데이터 파이프라인</a>, <a href="https://aws.amazon.com/glue/" data-linktype="external">붙이기</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-183"><a href="https://aws.amazon.com/datapipeline/" data-linktype="external">Data Pipeline</a>, <a href="https://aws.amazon.com/glue/" data-linktype="external">Glue</a></span></span></td>
<td><a href="https://azure.microsoft.com/services/data-factory/" data-linktype="external"><span data-ttu-id="24fb8-184">Data Factory</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-185">여러 컴퓨팅 및 스토리지 서비스 사이에서 데이터를 처리 및 이동하고 온-프레미스 데이터 원본을 지정된 간격으로 처리 및 이동합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-185">Processes and moves data between different compute and storage services, as well as on-premises data sources at specified intervals.</span></span> <span data-ttu-id="24fb8-186">데이터 파이프라인을 생성, 예약, 오케스트레이션 및 관리 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-186">Create, schedule, orchestrate, and manage data pipelines.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/glue/" data-linktype="external"><span data-ttu-id="24fb8-187">붙이기가</span><span class="sxs-lookup"><span data-stu-id="24fb8-187">Glue</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/data-catalog/" data-linktype="external"><span data-ttu-id="24fb8-188">Data Catalog</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-189">엔터프라이즈 데이터 원본에 대 한 등록 시스템 및 검색 시스템 역할을 하는 완전히 관리 되는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-189">A fully managed service that serves as a system of registration and system of discovery for enterprise data sources</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/dynamodb" data-linktype="external"><span data-ttu-id="24fb8-190">Dynamo DB</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-191"><a href="https://azure.microsoft.com/services/storage/tables" data-linktype="external">Table Storage</a>, <a href="https://azure.microsoft.com/services/cosmos-db" data-linktype="external">Cosmos DB</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-191"><a href="https://azure.microsoft.com/services/storage/tables" data-linktype="external">Table Storage</a>, <a href="https://azure.microsoft.com/services/cosmos-db" data-linktype="external">Cosmos DB</a></span></span></td>
<td><span data-ttu-id="24fb8-192">대규모 반 구조화 된 데이터 집합을 사용한 신속한 개발을 위한 NoSQL 키-값 저장소입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-192">NoSQL key-value store for rapid development using massive semi-structured datasets.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="compute"><span data-ttu-id="24fb8-213">Compute</span><span class="sxs-lookup"></span></h2>
<h3 id="virtual-servers"><span data-ttu-id="ace12-101">Virtual servers</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="ace12-102">AWS 서비스</span><span class="sxs-lookup"><span data-stu-id="ace12-102">AWS service</span></span></th>
<th><span data-ttu-id="ace12-103">Azure 서비스</span><span class="sxs-lookup"><span data-stu-id="ace12-103">Azure service</span></span></th>
<th><span data-ttu-id="ace12-104">설명</span><span class="sxs-lookup"><span data-stu-id="ace12-104">Description</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/ec2/" data-linktype="external"><span data-ttu-id="ace12-105">EC2(Elastic Compute Cloud) 인스턴스</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/virtual-machines/" data-linktype="external"><span data-ttu-id="ace12-106">Virtual Machines</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-107">가상 서버에서는 사용자가 OS 및 서버 소프트웨어를 배포, 관리 및 유지 관리할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-107">Virtual servers allow users to deploy, manage, and maintain OS and server software.</span></span> <span data-ttu-id="ace12-108">인스턴스 유형은 CPU/RAM 조합에 따라 달라집니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-108">Instance types provide combinations of CPU/RAM.</span></span> <span data-ttu-id="ace12-109">사용자는 사용한 양만큼 요금을 내며 유연하게 크기를 변경할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-109">Users pay for what they use with the flexibility to change sizes.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/batch/" data-linktype="external"><span data-ttu-id="ace12-110">Batch</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/batch/" data-linktype="external"><span data-ttu-id="ace12-111">Batch</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-112">클라우드에서 대규모 병렬 및 고성능 컴퓨팅 애플리케이션을 효율적으로 실행합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-112">Run large-scale parallel and high-performance computing applications efficiently in the cloud.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/autoscaling/" data-linktype="external"><span data-ttu-id="ace12-113">자동 크기 조정</span><span class="sxs-lookup"><span data-stu-id="ace12-113">Auto Scaling</span></span></a></td>
<td><a href="/ko-kr/azure/virtual-machine-scale-sets/virtual-machine-scale-sets-overview" data-linktype="absolute-path"><span data-ttu-id="ace12-114">Virtual Machine Scale Sets</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-115">VM 인스턴스 수를 자동으로 변경할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-115">Allows you to automatically change the number of VM instances.</span></span> <span data-ttu-id="ace12-116">플랫폼에서 인스턴스를 추가하거나 제거할지 결정하는 정의된 메트릭 및 임계값을 설정합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-116">You set defined metric and thresholds that determine if the platform adds or removes instances.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/vmware/" data-linktype="external"><span data-ttu-id="ace12-117">AWS의 VMware 클라우드</span><span class="sxs-lookup"><span data-stu-id="ace12-117">VMware Cloud on AWS</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/azure-vmware-cloudsimple/" data-linktype="external"><span data-ttu-id="ace12-118">CloudSimple에의 한 VMware</span><span class="sxs-lookup"><span data-stu-id="ace12-118">VMware by CloudSimple</span></span></a></td>
<td><span data-ttu-id="ace12-119">Azure VMware 솔루션을 사용 하 여 클라우드로 VMware 기반 엔터프라이즈 워크 로드를 재배포 하 고 확장 합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-119">Redeploy and extend your VMware-based enterprise workloads to Azure with Azure VMware Solution by CloudSimple.</span></span> <span data-ttu-id="ace12-120">네트워크, 보안 또는 데이터 보호 정책을 방해 하지 않고 Azure에서 워크 로드를 관리 하는 데 이미 알고 있는 VMware 도구를 계속 사용 하세요.</span><span class="sxs-lookup"><span data-stu-id="ace12-120">Keep using the VMware tools you already know to manage workloads on Azure without disrupting network, security, or data protection policies.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/hpc/parallelcluster/" data-linktype="external"><span data-ttu-id="ace12-121">병렬 클러스터</span><span class="sxs-lookup"><span data-stu-id="ace12-121">Parallel Cluster</span></span></a></td>
<td><a href="https://azure.microsoft.com/features/azure-cyclecloud/" data-linktype="external"><span data-ttu-id="ace12-122">CycleCloud</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-123">모든 규모의 HPC 및 빅 컴퓨팅 클러스터 만들기, 관리, 작동 및 최적화</span><span class="sxs-lookup"><span data-stu-id="ace12-123">Create, manage, operate, and optimize HPC and big compute clusters of any scale</span></span></td>
</tr>
</tbody>
</table>
<h3 id="containers-and-container-orchestrators"><span data-ttu-id="ace12-124">컨테이너 및 컨테이너 orchestrator</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="ace12-125">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="ace12-126">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="ace12-127">설명</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/ecs/" data-linktype="external"><span data-ttu-id="ace12-128">Elastic Container Service (ECS)</span><span class="sxs-lookup"></span></a><br/><br/><a href="https://aws.amazon.com/fargate/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="ace12-129">Fargate</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/container-instances/" data-linktype="external"><span data-ttu-id="ace12-130">Container Instances</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-131">Azure Container Instances는 가상 머신을 프로비전하거나 더 높은 수준의 오케스트레이션 서비스를 도입하지 않고도 Azure에서 컨테이너를 가장 빠르고 간단하게 실행하는 방법입니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-131">Azure Container Instances is the fastest and simplest way to run a container in Azure, without having to provision any virtual machines or adopt a higher-level orchestration service.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/ecr/" data-linktype="external"><span data-ttu-id="ace12-132">탄력적 Container Registry</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/container-registry/" data-linktype="external"><span data-ttu-id="ace12-133">Container Registry</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-134">고객을 Docker 형식 이미지를 저장할 수 있게 합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-134">Allows customers to store Docker formatted images.</span></span> <span data-ttu-id="ace12-135">Azure에 배포되는 모든 컨테이너 유형을 만드는 데 사용됩니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-135">Used to create all types of container deployments on Azure.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/eks/" data-linktype="external"><span data-ttu-id="ace12-136">탄Elastic Kubernetes Service (EKS)</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/kubernetes-service/" data-linktype="external"><span data-ttu-id="ace12-137">AKS(Azure Kubernetes Service)</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-138">Kubernetes를 사용하여 오케스트레이션 및 컨테이너화된 애플리케이션을 배포합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-138">Deploy orchestrated containerized applications with Kubernetes.</span></span> <span data-ttu-id="ace12-139">자동 업그레이드 및 기본 제공 작업 콘솔을 통해 모니터링 및 클러스터 관리를 간소화합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-139">Simplify monitoring and cluster management through auto upgrades and a built-in operations console.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/app-mesh/" data-linktype="external"><span data-ttu-id="ace12-140">앱 메시</span><span class="sxs-lookup"><span data-stu-id="ace12-140">App Mesh</span></span></a></td>
<td><a href="/ko-kr/azure/service-fabric-mesh/service-fabric-mesh-overview" data-linktype="absolute-path"><span data-ttu-id="ace12-141">Service Fabric Mesh</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="ace12-142">개발자가 가상 머신, 스토리지 또는 네트워킹을 관리하지 않고 마이크로 서비스 애플리케이션을 배포할 수 있는 완전히 관리되는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-142">Fully managed service that enables developers to deploy microservices applications without managing virtual machines, storage, or networking.</span></span></td>
</tr>
</tbody>
</table>

<h3 id="serverless"><span data-ttu-id="ace12-145">Serverless</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="ace12-125">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="ace12-126">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="ace12-127">설명</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/lambda/" data-linktype="external"><span data-ttu-id="ace12-149">Lambda</span><span class="sxs-lookup"><span data-stu-id="ace12-149">Lambda</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/functions/" data-linktype="external"><span data-ttu-id="ace12-150">함수</span><span class="sxs-lookup"><span data-stu-id="ace12-150">Functions</span></span></a></td>
<td><span data-ttu-id="ace12-151">서버 프로비전 또는 관리 없이 이벤트나 일정에 대한 응답으로 백 엔드 프로세스를 실행하고 시스템을 통합합니다.</span><span class="sxs-lookup"><span data-stu-id="ace12-151">Integrate systems and run backend processes in response to events or schedules without provisioning or managing servers.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="database"><span data-ttu-id="24fb8-214">데이터베이스</span><span class="sxs-lookup"><span data-stu-id="24fb8-214">Database</span></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="baec2-101">Type</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="baec2-102">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="baec2-103">Azure 서비스</span></span></th>
<th><span data-ttu-id="baec2-104">설명</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><span data-ttu-id="baec2-105">관계형 데이터베이스</span><span class="sxs-lookup"></span></td>
<td><a href="https://aws.amazon.com/rds/" data-linktype="external"><span data-ttu-id="baec2-106">RDS</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/sql-database/" data-linktype="external"><span data-ttu-id="baec2-107">SQL 데이터베이스</span><span class="sxs-lookup"></span></a><br/><br/><a href="https://azure.microsoft.com/services/mysql/" data-linktype="external"><span data-ttu-id="baec2-108">Database for MySQL</span></a><br/><br/><a href="https://azure.microsoft.com/services/postgresql/" data-linktype="external"><span data-ttu-id="baec2-109">Database for PostgreSQL</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="baec2-110">복원 력, 크기 조정 및 유지 관리가 주로 플랫폼에서 처리 되는 관리 되는 관계형 데이터베이스 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="baec2-110">Managed relational database service where resiliency, scale, and maintenance are primarily handled by the platform.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="baec2-111">NoSQL/문서</span><span class="sxs-lookup"><span data-stu-id="baec2-111">NoSQL / Document</span></span></td>
<td><a href="https://aws.amazon.com/dynamodb/" data-linktype="external"><span data-ttu-id="baec2-112">DynamoDB</span></span></a><br/><br/><a href="https://aws.amazon.com/simpledb/" data-linktype="external"><span data-ttu-id="baec2-113">SimpleDB</span><span class="sxs-lookup"><span data-stu-id="baec2-113">SimpleDB</span></span></a><br/><br/><a href="https://aws.amazon.com/documentdb/" data-linktype="external"><span data-ttu-id="baec2-114">Amazon DocumentDB</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/cosmos-db/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="baec2-115">Cosmos DB</span></span></a></td>
<td><span data-ttu-id="baec2-116">키-값, 문서, 그래프, 열 형식 등, 기본적으로 여러 데이터 모델을 지원하는 글로벌 분산 다중 모델 데이터베이스입니다.</span><span class="sxs-lookup"><span data-stu-id="baec2-116">A globally distributed, multi-model database that natively supports multiple data models: key-value, documents, graphs, and columnar.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="baec2-117">캐싱</span><span class="sxs-lookup"><span data-stu-id="baec2-117">Caching</span></span></td>
<td><a href="https://aws.amazon.com/elasticache/" data-linktype="external"><span data-ttu-id="baec2-118">ElastiCache</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/cache/" data-linktype="external"><span data-ttu-id="baec2-119">Cache for Redis</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="baec2-120">일반적으로 데이터베이스에서 트랜잭션 이외 작업을 오프로드하는 데 사용되는 고성능 저장소를 제공하는 메모리 내 기반 분산형 캐싱 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="baec2-120">An in-memory–based, distributed caching service that provides a high-performance store typically used to offload nontransactional work from a database.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="baec2-121">데이터베이스 마이그레이션</span><span class="sxs-lookup"></span></td>
<td><a href="https://aws.amazon.com/dms/" data-linktype="external"><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/campaigns/database-migration/" data-linktype="external"><span data-ttu-id="baec2-123">Database Migration Service</span></span></a></td>
<td><span data-ttu-id="baec2-124">데이터베이스 스키마 및 데이터를 한 데이터베이스 형식에서 클라우드의 특정 데이터베이스 기술로 마이그레이션</span><span class="sxs-lookup"><span data-stu-id="baec2-124">Migration of database schema and data from one database format to a specific database technology in the cloud.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="devops-and-application-monitoring"><span data-ttu-id="24fb8-215">DevOps 및 응용 프로그램 모니터링</span><span class="sxs-lookup"></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-216">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-217">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-218">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><span data-ttu-id="24fb8-219"><a href="https://aws.amazon.com/cloudwatch/" data-linktype="external">Cloudwatch</a>, <a href="https://aws.amazon.com/xray/" data-linktype="external">X 광선</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-219"><a href="https://aws.amazon.com/cloudwatch/" data-linktype="external">CloudWatch</a>, <a href="https://aws.amazon.com/xray/" data-linktype="external">X-Ray</a></span></span></td>
<td><a href="https://azure.microsoft.com/services/monitor/" data-linktype="external"><span data-ttu-id="24fb8-220">모니터</span><span class="sxs-lookup"><span data-stu-id="24fb8-220">Monitor</span></span></a></td>
<td><span data-ttu-id="24fb8-221">클라우드 및 온-프레미스 환경에서 원격 분석을 수집, 분석 및 작동 하는 포괄적인 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-221">Comprehensive solution for collecting, analyzing, and acting on telemetry from your cloud and on-premises environments.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/codedeploy/" data-linktype="external"><span data-ttu-id="24fb8-222">CodeDeploy</span><span class="sxs-lookup"></span></a> <br/><br/><a href="https://aws.amazon.com/codecommit/" data-linktype="external"><span data-ttu-id="24fb8-223">CodeCommit</span><span class="sxs-lookup"></span></a> <br/><br/><a href="https://aws.amazon.com/codepipeline/" data-linktype="external"><span data-ttu-id="24fb8-224">CodePipeline</span><span class="sxs-lookup"></a></td>
<td><a href="https://azure.microsoft.com/services/devops/" data-linktype="external"><span data-ttu-id="24fb8-225">DevOps</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-226">공동으로 코드를 개발하기 위한 클라우드 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-226">A cloud service for collaborating on code development.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/products/developer-tools/" data-linktype="external"><span data-ttu-id="24fb8-227">개발자 도구</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/tools/" data-linktype="external"><span data-ttu-id="24fb8-228">개발자 도구</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-229">다중 플랫폼 확장 가능 앱 및 서비스를 빌드, 디버깅, 배포, 진단 및 관리 하기 위한 도구 모음입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-229">Collection of tools for building, debugging, deploying, diagnosing, and managing multiplatform scalable apps and services.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/codebuild/" data-linktype="external"><span data-ttu-id="24fb8-230">CodeBuild</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/devops/" data-linktype="external"><span data-ttu-id="24fb8-231">DevOps</span><span class="sxs-lookup"></a></td>
<td><span data-ttu-id="24fb8-232">연속 통합과 배포를 지원하는 완전 관리되는 빌드 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-232">Fully managed build service that supports continuous integration and deployment.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cli/" data-linktype="external"><span data-ttu-id="24fb8-233">명령줄 인터페이스</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/cli/azure/install-azure-cli" data-linktype="absolute-path"><span data-ttu-id="24fb8-234">CLI</span><span class="sxs-lookup"><span data-stu-id="24fb8-234">CLI</span></span></a> <br/><br/><a href="/ko-kr/powershell/azure/overview" data-linktype="absolute-path"><span data-ttu-id="24fb8-235">PowerShell</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-236">모든 클라우드 서비스에서 기본 REST API 맨 위에 빌드되는 다양한 프로그래밍 언어별 래퍼를 사용하여 솔루션을 더 쉽게 만들 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-236">Built on top of the native REST API across all cloud services, various programming language-specific wrappers provide easier ways to create solutions.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/opsworks/" data-linktype="external"><span data-ttu-id="24fb8-237">OpsWorks(Chef 기반)</span><span class="sxs-lookup"><span data-stu-id="24fb8-237">OpsWorks (Chef-based)</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/automation/" data-linktype="external"><span data-ttu-id="24fb8-238">Automation</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-239">모든 형태 및 크기의 애플리케이션을 구성 및 운영하고 리소스 모음을 만들고 관리하기 위한 템플릿을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-239">Configures and operates applications of all shapes and sizes, and provides templates to create and manage a collection of resources.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cloudformation/" data-linktype="external"><span data-ttu-id="24fb8-240">CloudFormation</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/features/resource-manager/" data-linktype="external"><span data-ttu-id="24fb8-241">Resource Manager</span><span class="sxs-lookup"></span></a> <br/><br/><a href="/ko-kr/azure/virtual-machines/extensions/features-windows?toc=%2fazure%2fvirtual-machines%2fwindows%2ftoc.json" data-linktype="absolute-path"><span data-ttu-id="24fb8-242">VM 확장</span><span class="sxs-lookup"><span data-stu-id="24fb8-242">VM extensions</span></span></a> <br/><br/><a href="https://azure.microsoft.com/services/automation" data-linktype="external"><span data-ttu-id="24fb8-243">Azure Automation</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-244">오류가 발생하기 쉽고 자주 반복되는, 장기적인 수동 IT 작업을 자동화하는 방법을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-244">Provides a way for users to automate the manual, long-running, error-prone, and frequently repeated IT tasks.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="management"><span data-ttu-id="24fb8-267">관리</span><span class="sxs-lookup"></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-216">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-217">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-218">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/premiumsupport/technology/trusted-advisor/" data-linktype="external"><span data-ttu-id="24fb8-271">Trusted Advisor</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/advisor/" data-linktype="external"><span data-ttu-id="24fb8-272">Advisor</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-273">구독자가 모범 사례와 최적 구성을 사용할 수 있도록 클라우드 리소스 구성 및 보안에 대 한 분석을 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-273">Provides analysis of cloud resource configuration and security so subscribers can ensure they're making use of best practices and optimum configurations.</span></span></td>
</tr>
<tr>
<td><a href="https://docs.aws.amazon.com/awsaccountbilling/latest/aboutv2/billing-reports-gettingstarted-turnonreports.html" data-linktype="external"><span data-ttu-id="24fb8-274">사용량 및 청구 보고서</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/billing/billing-usage-rate-card-overview" data-linktype="absolute-path"><span data-ttu-id="24fb8-275">청구 API</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-276">시간, 조직 또는 제품 리소스에 따라 리소스 사용에 대한 청구 데이터를 생성, 모니터링, 예측 및 공유하는 데 도움이 되는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-276">Services to help generate, monitor, forecast, and share billing data for resource usage by time, organization, or product resources.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/console/" data-linktype="external"><span data-ttu-id="24fb8-277">관리 콘솔</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/features/azure-portal/" data-linktype="external"><span data-ttu-id="24fb8-278">포털</span><span class="sxs-lookup"></a></td>
<td><span data-ttu-id="24fb8-279">클라우드 리소스 작성, 배포 및 운영을 간소화하는 통합 관리 콘솔입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-279">A unified management console that simplifies building, deploying, and operating your cloud resources.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/application-discovery" data-linktype="external"><span data-ttu-id="24fb8-280">응용 프로그램 검색 서비스</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/azure-migrate/" data-linktype="external"><span data-ttu-id="24fb8-281">마이그레이션</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-282">Azure로 마이그레이션하기 위한 온-프레미스 워크 로드를 평가 하 고, 성능 기반 크기 조정을 수행 하 고, 비용 예측을 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-282">Assesses on-premises workloads for migration to Azure, performs performance-based sizing, and provides cost estimations.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/systems-manager/" data-linktype="external"><span data-ttu-id="24fb8-283">EC2 Systems Manager</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/monitor/" data-linktype="external"><span data-ttu-id="24fb8-284">모니터</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-285">클라우드 및 온-프레미스 환경에서 원격 분석을 수집, 분석 및 작동 하는 포괄적인 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-285">Comprehensive solution for collecting, analyzing, and acting on telemetry from your cloud and on-premises environments.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/premiumsupport/technology/personal-health-dashboard/" data-linktype="external"><span data-ttu-id="24fb8-286">개인 상태 대시보드</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/resource-health/resource-health-overview" data-linktype="absolute-path"><span data-ttu-id="24fb8-287">리소스 상태</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-288">리소스 상태 유지 관리를 위한 권장 조치와 리소스의 상태에 관한 상세 정보를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-288">Provides detailed information about the health of resources as well as recommended actions for maintaining resource health.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cloudtrail/" data-linktype="external"><span data-ttu-id="24fb8-289">CloudTrail</span><span class="sxs-lookup"><span data-stu-id="24fb8-289">CloudTrail</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/monitor/" data-linktype="external"><span data-ttu-id="24fb8-290">모니터</span><span class="sxs-lookup"></a></td>
<td><span data-ttu-id="24fb8-291">클라우드 및 온-프레미스 환경에서 원격 분석을 수집, 분석 및 작동 하는 포괄적인 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-291">Comprehensive solution for collecting, analyzing, and acting on telemetry from your cloud and on-premises environments.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cloudwatch" data-linktype="external"><span data-ttu-id="24fb8-292">CloudWatch</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/azure-monitor/app/app-insights-overview" data-linktype="absolute-path"><span data-ttu-id="24fb8-293">Application Insights</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-294">Application Insights는 개발자와 DevOps 전문가를 위한 확장 가능한 APM (응용 프로그램 성능 관리) 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-294">Application Insights, is an extensible Application Performance Management (APM) service for developers and DevOps professionals.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/aws-cost-management/" data-linktype="external"><span data-ttu-id="24fb8-295">비용 탐색기</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/cost-management/" data-linktype="external"><span data-ttu-id="24fb8-296">Cost Management</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-297">클라우드 비용을 최적화 하 고 클라우드 잠재력을 극대화 하세요.</span><span class="sxs-lookup"><span data-stu-id="24fb8-297">Optimize cloud costs while maximizing cloud potential.</span></span></td>
</tr>
</tbody>
</table>
<h2 id="messaging-and-eventing"><span data-ttu-id="24fb8-298">메시징 및 이벤트</span><span class="sxs-lookup"></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="f014d-101">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="f014d-102">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="f014d-103">설명</span><span class="sxs-lookup"><span data-stu-id="f014d-103">Description</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/sqs/" data-linktype="external"><span data-ttu-id="f014d-104">SQS(Simple Queue Service)</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/storage/queues/" data-linktype="external"><span data-ttu-id="f014d-105">Queue Storage</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="f014d-106">분리된 애플리케이션 구성 요소 간의 통신을 위한 관리되는 메시지 대기열 서비스를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="f014d-106">Provides a managed message queueing service for communicating between decoupled application components.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/sqs/" data-linktype="external"><span data-ttu-id="f014d-107">SQS(Simple Queue Service)</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/service-bus/" data-linktype="external"><span data-ttu-id="f014d-108">Service Bus</span><span class="sxs-lookup"><span data-stu-id="f014d-108">Service Bus</span></span></a></td>
<td><span data-ttu-id="f014d-109">신뢰할 수 있는 메시지 큐 및 지속형 게시/구독 메시징을 포함하여 클라우드 기반, 메시지 지향 미들웨어 기술 집합을 지원합니다.</span><span class="sxs-lookup"><span data-stu-id="f014d-109">Supports a set of cloud-based, message-oriented middleware technologies including reliable message queuing and durable publish/subscribe messaging.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/sns/" data-linktype="external"><span data-ttu-id="f014d-110">간단한 알림 서비스</span><span class="sxs-lookup"></a></td>
<td><a href="https://azure.microsoft.com/services/event-grid/" data-linktype="external"><span data-ttu-id="f014d-111">Event Grid</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="f014d-112">게시/구독 모델을 사용 하 여 균일 한 이벤트 사용을 허용 하는 완전히 관리 되는 이벤트 라우팅 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="f014d-112">A fully managed event routing service that allows for uniform event consumption using a publish/subscribe model.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="mobile-services"><span data-ttu-id="24fb8-299">모바일 서비스</span><span class="sxs-lookup"></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-300">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-301">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-302">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/blogs/aws/aws-mobile-hub-build-test-and-monitor-mobile-applications/" data-linktype="external"><span data-ttu-id="24fb8-303">Mobile Hub</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-center/" data-linktype="external"><span data-ttu-id="24fb8-304">App Center</span><span class="sxs-lookup"></span></a> <br/><br/><a href="https://azure.microsoft.com/features/xamarin/" data-linktype="external"><span data-ttu-id="24fb8-305">Xamarin 앱</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-306">모바일 솔루션의 빠른 개발을 위한 백엔드 모바일 서비스를 나타내고 ID 관리, 데이터 동기화, 디바이스 간 스토리지 및 알림을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-306">Provides backend mobile services for rapid development of mobile solutions, identity management, data synchronization, and storage and notifications across devices.</span></span></td>
</tr>
<tr>
<td><a href="https://docs.aws.amazon.com/mobile-sdk/" data-linktype="external"><span data-ttu-id="24fb8-307">모바일 SDK</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-center/" data-linktype="external"><span data-ttu-id="24fb8-308">App Center</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-309">모바일 디바이스를 위한 플랫폼 간 및 원시 앱을 신속하게 개발할 수 있는 기술을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-309">Provides the technology to rapidly build cross-platform and native apps for mobile devices.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cognito/" data-linktype="external"><span data-ttu-id="24fb8-310">Cognito</span><span class="sxs-lookup"></a></td>
<td><a href="https://azure.microsoft.com/services/app-center/" data-linktype="external"><span data-ttu-id="24fb8-311">App Center</span><span class="sxs-lookup"><span data-stu-id="24fb8-311">App Center</span></span></a></td>
<td><span data-ttu-id="24fb8-312">모바일 애플리케이션에 대한 인증 기능을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-312">Provides authentication capabilities for mobile applications.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/device-farm/" data-linktype="external"><span data-ttu-id="24fb8-313">Device Farm</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-center/" data-linktype="external"><span data-ttu-id="24fb8-314">App Center</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-315">모바일 애플리케이션 테스트를 지원하는 서비스를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-315">Provides services to support testing mobile applications.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/mobileanalytics/" data-linktype="external"><span data-ttu-id="24fb8-316">Mobile Analytics</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-center/" data-linktype="external"><span data-ttu-id="24fb8-317">App Center</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-318">모바일 애플리케이션 서비스 품질의 디버그 및 분석을 위한 모니터링과 피드백 컬렉션을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-318">Supports monitoring, and feedback collection for the debugging and analysis of a mobile application service quality.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="networking"><span data-ttu-id="24fb8-325">네트워킹</span><span class="sxs-lookup"></span></h2>
<table>
<thead>
<tr>
<th><span data-ttu-id="09aa3-101">Area</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="09aa3-102">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="09aa3-103">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="09aa3-104">설명</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><span data-ttu-id="09aa3-105">클라우드 가상 네트워킹</span><span class="sxs-lookup"></span></td>
<td><a href="https://aws.amazon.com/vpc/" data-linktype="external"><span data-ttu-id="09aa3-106">VPC(Virtual Private Cloud)</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/virtual-network/" data-linktype="external"><span data-ttu-id="09aa3-107">Virtual Network</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-108">클라우드에서 격리된 프라이빗 환경을 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-108">Provides an isolated, private environment in the cloud.</span></span> <span data-ttu-id="09aa3-109">사용자는 고유한 IP 주소 범위를 선택하고 서브넷을 만들고 경로 테이블과 네트워크 게이트웨이를 구성하는 등 가상 네트워킹 환경을 제어할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-109">Users have control over their virtual networking environment, including selection of their own IP address range, creation of subnets, and configuration of route tables and network gateways.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="09aa3-110">크로스 프레미스 연결</span><span class="sxs-lookup"></span></td>
<td><a href="https://docs.aws.amazon.com/vpn/latest/s2svpn/VPC_VPN.html" data-linktype="external"><span data-ttu-id="09aa3-111">VPN Gateway</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/vpn-gateway/vpn-gateway-about-vpngateways" data-linktype="absolute-path"><span data-ttu-id="09aa3-112">VPN Gateway</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-113">Azure 가상 네트워크를 다른 Azure 가상 네트워크 또는 고객 온-프레미스 네트워크 (사이트 간)에 연결 합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-113">Connects Azure virtual networks to other Azure virtual networks, or customer on-premises networks (Site To Site).</span></span> <span data-ttu-id="09aa3-114">최종 사용자가 VPN 터널링 (지점 및 사이트 간)을 통해 Azure 서비스에 연결할 수 있도록 허용 합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-114">Allows end users to connect to Azure services through VPN tunneling (Point To Site).</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="09aa3-115">DNS 관리</span><span class="sxs-lookup"></span></td>
<td><a href="https://aws.amazon.com/route53/" data-linktype="external"><span data-ttu-id="09aa3-116">Route 53</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/dns/" data-linktype="external"><span data-ttu-id="09aa3-117">DNS</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-118">다른 Azure 서비스와 동일한 자격 증명, 청구 및 지원 계약을 사용하여 DNS 레코드를 관리합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-118">Manage your DNS records using the same credentials and billing and support contract as your other Azure services</span></span></td>
</tr>
<tr>
<td> </td>
<td><a href="https://aws.amazon.com/route53/" data-linktype="external"><span data-ttu-id="09aa3-119">53</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/traffic-manager/" data-linktype="external"><span data-ttu-id="09aa3-120">Traffic Manager</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-121">도메인 이름을 호스트하고, 사용자를 인터넷 애플리케이션으로 라우팅하고, 사용자 요청을 데이터 센터에 연결하고, 앱에 대한 트래픽을 관리하고, 자동 장애 조치(failover)로 앱 가용성을 향상하는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-121">A service that hosts domain names, plus routes users to Internet applications, connects user requests to datacenters, manages traffic to apps, and improves app availability with automatic failover.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="09aa3-122">전용 네트워크</span><span class="sxs-lookup"><span data-stu-id="09aa3-122">Dedicated network</span></span></td>
<td><a href="https://aws.amazon.com/directconnect/" data-linktype="external"><span data-ttu-id="09aa3-123">직접 연결</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/expressroute/" data-linktype="external"><span data-ttu-id="09aa3-124">ExpressRoute</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-125">인터넷을 통하지 않고 한 위치에서 클라우드 공급자로 전용 프라이빗망 연결을 설정합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-125">Establishes a dedicated, private network connection from a location to the cloud provider (not over the Internet).</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="09aa3-126">부하 분산</span><span class="sxs-lookup"></span></td>
<td><a href="https://docs.aws.amazon.com/elasticloadbalancing/latest/network/introduction.html" data-linktype="external"><span data-ttu-id="09aa3-127">네트워크 Load Balancer</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/load-balancer/" data-linktype="external"><span data-ttu-id="09aa3-128">Load Balancer</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-129">Azure Load Balancer 계층 4 (TCP 또는 UDP)에서 트래픽 부하를 분산 합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-129">Azure Load Balancer load-balances traffic at layer 4 (TCP or UDP).</span></span></td>
</tr>
<tr>
<td> </td>
<td><a href="https://docs.aws.amazon.com/elasticloadbalancing/latest/application/introduction.html" data-linktype="external"><span data-ttu-id="09aa3-130">Application Load Balancer</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/application-gateway/" data-linktype="external"><span data-ttu-id="09aa3-131">Application Gateway</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="09aa3-132">Application Gateway는 계층 7 부하 분산 장치입니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-132">Application Gateway is a layer 7 load balancer.</span></span> <span data-ttu-id="09aa3-133">SSL 종료, 쿠키 기반 세션 선호도 및 부하 분산 트래픽에 대 한 라운드 로빈을 지원 합니다.</span><span class="sxs-lookup"><span data-stu-id="09aa3-133">It supports SSL termination, cookie-based session affinity, and round robin for load-balancing traffic.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="security-identity-and-access"><span data-ttu-id="24fb8-326">보안, ID 및 액세스</span><span class="sxs-lookup"></span></h2>
<h3 id="authentication-and-authorization"><span data-ttu-id="24fb8-327">인증 및 권한 부여</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-328">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-329">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-330">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/iam/" data-linktype="external"><span data-ttu-id="24fb8-331">IAM(ID 및 액세스 관리)</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/active-directory/" data-linktype="external"><span data-ttu-id="24fb8-332">Azure Active Directory</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-333">데이터 보안 및 보호를 제공하면서 사용자가 서비스와 리소스에 대한 액세스를 안전하게 제어하게 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-333">Allows users to securely control access to services and resources while offering data security and protection.</span></span> <span data-ttu-id="24fb8-334">사용자 및 그룹을 만들고 관리하며 사용 권한을 사용하여 리소스에 대한 액세스를 허용 및 거부합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-334">Create and manage users and groups, and use permissions to allow and deny access to resources.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/iam/" data-linktype="external"><span data-ttu-id="24fb8-335">IAM(ID 및 액세스 관리)</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/role-based-access-control/overview" data-linktype="absolute-path"><span data-ttu-id="24fb8-336">역할 기반 Access Control</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-337">RBAC(역할 기반 액세스 제어)는 Azure 리소스에 액세스할 수 있는 사용자, 해당 리소스로 수행할 수 있는 작업 및 액세스 권한이 있는 영역을 관리하는 데 도움을 줍니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-337">Role-based access control (RBAC) helps you manage who has access to Azure resources, what they can do with those resources, and what areas they have access to.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/organizations/" data-linktype="external"><span data-ttu-id="24fb8-338">Organizations</span><span class="sxs-lookup"></span></a></td>
<td><a href="/ko-kr/azure/azure-subscription-service-limits" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="24fb8-339">Subscription Management + RBAC</span></span></a></td>
<td><span data-ttu-id="24fb8-340">여러 계정 작업을 위한 보안 정책 및 역할 관리</span><span class="sxs-lookup"><span data-stu-id="24fb8-340">Security policy and role management for working with multiple accounts.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/iam/features/mfa/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-341">Multi-Factor Authentication</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/multi-factor-authentication/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-342">Multi-Factor Authentication</span></span></a></td>
<td><span data-ttu-id="24fb8-343">간단한 로그인 프로세스에 대 한 사용자 요구를 충족 하는 동시에 데이터 및 응용 프로그램에 대 한 액세스를 보호 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-343">Safeguard access to data and applications while meeting user demand for a simple sign-in process.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/directoryservice/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-344">Directory Service</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/active-directory-ds/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-345">Azure Active Directory Domain Services</span></span></a></td>
<td><span data-ttu-id="24fb8-346">는 Windows Server Active Directory와 완전히 호환 되는 도메인 가입, 그룹 정책, LDAP, Kerberos/NTLM 인증 등의 관리 되는 도메인 서비스를 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-346">Provides managed domain services such as domain join, group policy, LDAP, and Kerberos/NTLM authentication that are fully compatible with Windows Server Active Directory.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cognito/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-347">Cognito</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/active-directory-b2c/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-348">Azure Active Directory B2C</span></span></a></td>
<td><span data-ttu-id="24fb8-349">수억 개의 ID로 확장하는 소비자 지향 애플리케이션에 항상 사용 가능한 전역적인 ID 관리 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-349">A highly available, global, identity management service for consumer-facing applications that scales to hundreds of millions of identities.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/organizations/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-350">Organizations</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/azure-policy/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-351">Policy</span></span></a></td>
<td><span data-ttu-id="24fb8-352">Azure Policy는 정책을 만들고, 할당 및 관리하는 데 사용하는 Azure의 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-352">Azure Policy is a service in Azure that you use to create, assign, and manage policies.</span></span> <span data-ttu-id="24fb8-353">정책은 리소스에 대해 다양한 규칙과 효과를 적용하여 리소스가 회사 표준 및 서비스 수준 약정을 준수하도록 유지합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-353">These policies enforce different rules and effects over your resources, so those resources stay compliant with your corporate standards and service level agreements.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/organizations/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-354">Organizations</span></span></a></td>
<td><a href="/ko-kr/azure/governance/management-groups/" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="24fb8-355">Management Groups</span></span></a></td>
<td><span data-ttu-id="24fb8-356">Azure 관리 그룹은 구독 상위 수준의 범위를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-356">Azure management groups provide a level of scope above subscriptions.</span></span> <span data-ttu-id="24fb8-357">&quot;관리 그룹&quot;이라는 컨테이너에 구독을 구성하고 거버넌스 조건을 관리 그룹에 적용합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-357">You organize subscriptions into containers called &quot;management groups&quot; and apply your governance conditions to the management groups.</span></span> <span data-ttu-id="24fb8-358">관리 그룹에 속하는 모든 구독은 관리 그룹에 적용되는 조건을 자동으로 상속합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-358">All subscriptions within a management group automatically inherit the conditions applied to the management group.</span></span> <span data-ttu-id="24fb8-359">관리 그룹은 보유 하 고 있는 구독 유형에 관계 없이 대규모의 엔터프라이즈급 관리를 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-359">Management groups give you enterprise-grade management at a large scale, no matter what type of subscriptions you have.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="encryption"><span data-ttu-id="24fb8-360">암호화</span><span class="sxs-lookup"><span data-stu-id="24fb8-360">Encryption</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-361">AWS 서비스</span></span></th>
<th><span data-ttu-id="24fb8-362">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-363">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://docs.aws.amazon.com/kms/latest/developerguide/services-s3.html" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-364">Server-side encryption with Amazon S3 Key Management Service</span></span></a></td>
<td><a href="/ko-kr/azure/storage/storage-service-encryption" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="24fb8-365">Azure Storage Service Encryption</span></span></a></td>
<td><span data-ttu-id="24fb8-366">데이터를 안전하게 보호하고 조직의 보안 및 규정 준수 노력에 부합하는 데 도움이 됩니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-366">Helps you protect and safeguard your data and meet your organizational security and compliance commitments.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="24fb8-367"><a href="https://aws.amazon.com/kms" data-linktype="external">KMS (키 관리 서비스)</a>, <a href="https://aws.amazon.com/cloudhsm" data-linktype="external">CloudHSM</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-367"><a href="https://aws.amazon.com/kms" data-linktype="external">Key Management Service (KMS)</a>, <a href="https://aws.amazon.com/cloudhsm" data-linktype="external">CloudHSM</a></span></span></td>
<td><a href="https://azure.microsoft.com/services/key-vault" data-linktype="external"><span data-ttu-id="24fb8-368">Key Vault</span><span class="sxs-lookup"><span data-stu-id="24fb8-368">Key Vault</span></span></a></td>
<td><span data-ttu-id="24fb8-369">HSM(하드웨어 보안 모듈)에 저장된 암호화 키를 관리, 생성 및 제어하는 방법을 제공하여 보안 솔루션을 제공하고 다른 서비스와 상호 작용합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-369">Provides security solution and works with other services by providing a way to manage, create, and control encryption keys stored in hardware security modules (HSM).</span></span></td>
</tr>
</tbody>
</table>
<h3 id="firewall"><span data-ttu-id="24fb8-370">Firewall</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-371">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-372">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-373">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/waf/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-374">Web Application Firewall</span></span></a></td>
<td><a href="/ko-kr/azure/application-gateway/application-gateway-web-application-firewall-overview" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="24fb8-375">Web Application Firewall</span></span></a></td>
<td><span data-ttu-id="24fb8-376">일반적인 웹 공격으로부터 웹 애플리케이션을 보호하는 방화벽입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-376">A firewall that protects web applications from common web exploits.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/waf/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-377">Web Application Firewall</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/azure-firewall/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-378">Firewall</span></span></a></td>
<td><span data-ttu-id="24fb8-379">HTTP가 아닌 프로토콜에 대 한 인바운드 보호, 모든 포트 및 프로토콜에 대 한 아웃 바운드 네트워크 수준 보호 및 아웃 바운드 HTTP/S에 대 한 응용 프로그램 수준 보호를 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-379">Provides inbound protection for non-HTTP/S protocols, outbound network-level protection for all ports and protocols, and application-level protection for outbound HTTP/S.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="security"><span data-ttu-id="24fb8-380">보안</span><span class="sxs-lookup"><span data-stu-id="24fb8-380">Security</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-381">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-382">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-383">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/inspector/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-384">Inspector</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/security-center/" data-linktype="external"><span data-ttu-id="24fb8-385">Security Center</span><span class="sxs-lookup"></span></a></td>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-386">An automated security assessment service that improves the security and compliance of applications.</span></span> <span class="sxs-lookup"><span data-stu-id="24fb8-387">Automatically assess applications for vulnerabilities or deviations from best practices.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/certificate-manager/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-388">Certificate Manager</span></span></a></td>
<td><a href="https://azure.microsoft.com/blog/internals-of-app-service-certificate/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-389">App Service Certificates available on the Portal</span></span></a></td>
<td><span data-ttu-id="24fb8-390">고객이 클라우드에서 인증서를 원활 하 게 만들고 관리 하 고 사용할 수 있도록 하는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-390">Service that allows customers to create, manage, and consume certificates seamlessly in the cloud.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/guardduty/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-391">GuardDuty</span></span></a></td>
<td><a href="https://azure.microsoft.com/features/azure-advanced-threat-protection/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-392">Advanced Threat Protection</span></span></a></td>
<td><span data-ttu-id="24fb8-393">온-프레미스와 클라우드에서 진화된 공격을 검색하고 조사합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-393">Detect and investigate advanced attacks on-premises and in the cloud.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/artifact/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-394">Artifact</span></span></a></td>
<td><a href="https://servicetrust.microsoft.com/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-395">Service Trust Portal</span></span></a></td>
<td><span data-ttu-id="24fb8-396">클라우드 서비스 전체에서 감사 보고서, 규정 준수 지침 및 신뢰 문서에 대한 액세스를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-396">Provides access to audit reports, compliance guides, and trust documents from across cloud services.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/shield/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-397">Shield</span></span></a></td>
<td><a href="/ko-kr/azure/security/azure-ddos-best-practices" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="24fb8-398">DDos Protection Service</span></span></a></td>
<td><span data-ttu-id="24fb8-399">클라우드 서비스에 DDoS(분산형 서비스 거부) 공격으로부터의 보호를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-399">Provides cloud services with protection from distributed denial of services (DDoS) attacks.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="storage"><span data-ttu-id="24fb8-402">스토리지</span><span class="sxs-lookup"><span data-stu-id="24fb8-402">Storage</span></span></h2>
<h3 id="object-storage"><span class="sxs-lookup"><span data-stu-id="b9e79-101">Object storage</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-102">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-103">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-104">설명</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/s3/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-105">Simple Storage Services (S3)</span></span></a></td>
<td><a href="/ko-kr/azure/storage/blobs/storage-blobs-introduction" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="b9e79-106">Blob storage</span></span></a></td>
<td><span data-ttu-id="b9e79-107">클라우드 애플리케이션, 콘텐츠 배포, 백업, 보관, 재해 복구, 빅 데이터 분석 등의 사용 사례에 대한 개체 스토리지 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-107">Object storage service, for use cases including cloud applications, content distribution, backup, archiving, disaster recovery, and big data analytics.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="virtual-server-disks"><span data-ttu-id="b9e79-108">Virtual server 디스크</span><span class="sxs-lookup"></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-109">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-110">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-111">설명</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/ebs/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-112">Elastic Block Store (EBS)</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/storage/disks/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-113">managed disks</span></span></a></td>
<td><span data-ttu-id="b9e79-114">I/O 집약적 읽기/쓰기 작업을 위해 최적화된 SSD 스토리지.</span><span class="sxs-lookup"><span data-stu-id="b9e79-114">SSD storage optimized for I/O intensive read/write operations.</span></span> <span data-ttu-id="b9e79-115">고성능 Azure virtual machine storage로 사용 합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-115">For use as high-performance Azure virtual machine storage.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="shared-files"><span class="sxs-lookup"><span data-stu-id="b9e79-116">Shared files</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-117">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-118">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-119">설명</span></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/efs/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-120">Elastic File System</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/storage/files/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-121">Files</span></span></a></td>
<td><span data-ttu-id="b9e79-122">빠르게 파일 시스템을 만들고 구성하며 공통 파일을 공유하기 위한 간단한 인터페이스를 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-122">Provides a simple interface to create and configure file systems quickly, and share common files.</span></span> <span data-ttu-id="b9e79-123">네트워크를 통해 파일에 액세스 하는 기존 프로토콜과 함께 사용할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-123">Can be used with traditional protocols that access files over a network.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="archiving-and-backup"><span data-stu-id="b9e79-124">Archiving and backup</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-125">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-126">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-127">설명</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/s3/storage-classes" data-linktype="external"><span data-ttu-id="b9e79-128">S3 IA(Infrequent Access)</span><span class="sxs-lookup"><span data-stu-id="b9e79-128">S3 Infrequent Access (IA)</span></span></a></td>
<td><a href="/ko-kr/azure/storage/blobs/storage-blob-storage-tiers" data-linktype="absolute-path"><span data-ttu-id="b9e79-129">저장소 쿨 계층</span><span class="sxs-lookup"><span data-stu-id="b9e79-129">Storage cool tier</span></span></a></td>
<td><span data-ttu-id="b9e79-130">쿨 저장소는 자주 액세스 하지 않으며 수명이 긴 데이터를 저장 하기 위한 저렴 한 계층입니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-130">Cool storage is a lower-cost tier for storing data that is infrequently accessed and long-lived.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="b9e79-131"><a href="https://aws.amazon.com/s3/storage-classes" data-linktype="external">S3 Glacier</a>, 심층 보관</span><span class="sxs-lookup"><span data-stu-id="b9e79-131"><a href="https://aws.amazon.com/s3/storage-classes" data-linktype="external">S3 Glacier</a>, Deep Archive</span></span></td>
<td><a href="/ko-kr/azure/storage/blobs/storage-blob-storage-tiers" data-linktype="absolute-path"><span data-ttu-id="b9e79-132">저장소 보관 액세스 계층</span><span class="sxs-lookup"><span data-stu-id="b9e79-132">Storage archive access tier</span></span></a></td>
<td><span data-ttu-id="b9e79-133">보관 스토리지는 스토리지 비용아 가장 낮고 핫 및 쿨 스토리지에 비해 데이터 검색 비용이 높습니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-133">Archive storage has the lowest storage cost and higher data retrieval costs compared to hot and cool storage.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/backup/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-134">Backup</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/backup/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-135">Backup</span></span></a></td>
<td><span data-ttu-id="b9e79-136">클라우드에서 파일과 폴더를 백업 및 복구 하 고 데이터 손실에 대 한 오프 사이트 보호를 제공 합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-136">Back up and recover files and folders from the cloud, and provide offsite protection against data loss.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="hybrid-storage"><span class="sxs-lookup"><span data-stu-id="b9e79-137">Hybrid storage</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-138">AWS 서비스</span><span class="sxs-lookup"></th>
<th><span data-ttu-id="b9e79-139">Azure 서비스</span><span class="sxs-lookup"></th>
<th><span data-ttu-id="b9e79-140">설명</span><span class="sxs-lookup"></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/storagegateway/" data-linktype="external"><span data-ttu-id="b9e79-141">Storage Gateway</span><span class="sxs-lookup"><span data-stu-id="b9e79-141">Storage Gateway</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/storsimple/" data-linktype="external"><span data-ttu-id="b9e79-142">StorSimple</span><span class="sxs-lookup"><span data-stu-id="b9e79-142">StorSimple</span></span></a></td>
<td><span data-ttu-id="b9e79-143">온-프레미스 IT 환경을 클라우드 스토리지에 통합합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-143">Integrates on-premises IT environments with cloud storage.</span></span> <span data-ttu-id="b9e79-144">데이터 관리 및 스토리지를 자동화하고 재해 복구를 지원합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-144">Automates data management and storage, plus supports disaster recovery.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/datasync/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-145">DataSync</span></span></a></td>
<td><a href="/ko-kr/azure/storage/files/storage-sync-files-planning" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="b9e79-146">File Sync</span></span></a></td>
<td><span data-ttu-id="b9e79-147">Azure Files는 서버를 사용 하지 않는 Azure 파일 공유를 직접 탑재 하거나 Azure File Sync를 사용 하 여 온-프레미스에서 Azure 파일 공유를 캐시 하는 두 가지 주요 방법으로 배포할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-147">Azure Files can be deployed in two main ways: by directly mounting the serverless Azure file shares or by caching Azure file shares on-premises using Azure File Sync.</span></span></td>
</tr>
</tbody>
</table>
<h3 id="bulk-data-transfer"><span class="sxs-lookup"><span data-stu-id="b9e79-148">Bulk data transfer</span></span></h3>
<table>
<thead>
<tr>
<th><span data-ttu-id="b9e79-149">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-150">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="b9e79-151">설명</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/snowball/disk/details/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-152">Import/Export Disk</span></span></a></td>
<td><a href="/ko-kr/azure/storage/common/storage-import-export-service" data-linktype="absolute-path"><span class="sxs-lookup"><span data-stu-id="b9e79-153">Import/Export</span></span></a></td>
<td><span data-ttu-id="b9e79-154">보안 디스크 및 어플라이언스를 사용하여 대용량 데이터를 전송하는 데이터 전송 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-154">A data transport solution that uses secure disks and appliances to transfer large amounts of data.</span></span> <span data-ttu-id="b9e79-155">전송 중에 데이터 보호도 제공합니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-155">Also offers data protection during transit.</span></span></td>
</tr>
<tr>
<td><span data-ttu-id="b9e79-156"><a href="https://aws.amazon.com/snowball/" data-linktype="external">Import/Export Snowball</a>, <a href="https://aws.amazon.com/snowball-edge/" data-linktype="external">Snowball Edge</a>, <a href="https://aws.amazon.com/snowmobile/" data-linktype="external">Snowmobile</a></span><span class="sxs-lookup"></span></td>
<td><a href="https://azure.microsoft.com/services/storage/databox/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="b9e79-157">Data Box</span></span></a></td>
<td><span data-ttu-id="b9e79-158">페타바이트-보안 데이터 저장 장치를 사용 하 여 Azure로/에서 대량의 데이터를 전송 하는 엑사바이트 규모의 데이터 전송 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="b9e79-158">Petabyte- to exabyte-scale data transport solution that uses secure data storage devices to transfer large amounts of data to and from Azure.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="web-applications"><span class="sxs-lookup"><span data-stu-id="24fb8-403">Web applications</span></span></h2>

<table>
<thead>
<tr>
<th><span data-ttu-id="24fb8-404">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-405">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-406">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><a href="https://aws.amazon.com/elasticbeanstalk/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-407">Elastic Beanstalk</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-service" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-408">App Service</span></span></a></td>
<td><span data-ttu-id="24fb8-409">웹 응용 프로그램 및 서비스를 배포 하 고 확장 하기 위해 사용 하기 쉬운 서비스를 제공 하는 관리 되는 호스팅 플랫폼입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-409">Managed hosting platform providing easy to use services for deploying and scaling web applications and services.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/api-gateway/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-410">API Gateway</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/api-management/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-411">API Management</span></span></a></td>
<td><span data-ttu-id="24fb8-412">API를 외부 및 내부 소비자에게 게시하기 위한 턴키 방식 솔루션입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-412">A turnkey solution for publishing APIs to external and internal consumers.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/cloudfront/" data-linktype="external"><span data-ttu-id="24fb8-413">CloudFront</span><span class="sxs-lookup"></span></a></td>
<td><a href="https://azure.microsoft.com/services/cdn/" data-linktype="external"><span data-ttu-id="24fb8-414">Content Delivery Network</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-415">오디오, 비디오, 애플리케이션, 이미지 및 기타 파일을 배달하는 글로벌 콘텐츠 배달 네트워크입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-415">A global content delivery network that delivers audio, video, applications, images, and other files.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/global-accelerator/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-416">Global Accelerator</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/frontdoor/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-417">Front Door</span></span></a></td>
<td><span data-ttu-id="24fb8-418">HTTP 부하 분산 및 경로 기반 라우팅 규칙을 사용 하 여 분산 마이크로 서비스 아키텍처를 단일 글로벌 응용 프로그램에 쉽게 연결 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-418">Easily join your distributed microservices architectures into a single global application using HTTP load balancing and path-based routing rules.</span></span> <span data-ttu-id="24fb8-419">API 구동 글로벌 작업을 사용 하 여 새 지역 및 수평 확장을 자동화 하 고, Azure의 백 엔드 마이크로 서비스에 대 한 독립 내결함성을 강화 합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-419">Automate turning up new regions and scale-out with API-driven global actions, and independent fault-tolerance to your back end microservices in Azure-or anywhere.</span></span></td>
</tr>
<tr>
<td><a href="https://aws.amazon.com/lightsail/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-420">LightSail</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/app-service/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-421">App Service</span></span></a></td>
<td><span data-ttu-id="24fb8-422">완전히 관리 되는 플랫폼에서 웹 앱을 빌드, 배포 및 확장 하세요.</span><span class="sxs-lookup"><span data-stu-id="24fb8-422">Build, deploy, and scale web apps on a fully managed platform.</span></span></td>
</tr>
</tbody>
</table>


<h2 id="miscellaneous"><span class="sxs-lookup"><span data-stu-id="24fb8-425">Miscellaneous</span></span></h2>
<table>
<thead>
<tr>
<th><span class="sxs-lookup"><span data-stu-id="24fb8-426">Area</span></span></th>
<th><span data-ttu-id="24fb8-427">AWS 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-428">Azure 서비스</span><span class="sxs-lookup"></span></th>
<th><span data-ttu-id="24fb8-429">Description</span><span class="sxs-lookup"></span></th>
</tr>
</thead>
<tbody>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-430">Backend process logic</span></span></td>
<td><a href="https://aws.amazon.com/step-functions/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-431">Step Functions</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/logic-apps/" data-linktype="external"><span data-ttu-id="24fb8-432">Logic Apps</span><span class="sxs-lookup"><span data-stu-id="24fb8-432">Logic Apps</span></span></a></td>
<td><span data-ttu-id="24fb8-433">통합 문제를 줄이기 위해 바로 사용할 수 있는 커넥터를 통해 분산형 애플리케이션을 빌드하는 클라우드 기술입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-433">Cloud technology to build distributed applications using out-of-the-box connectors to reduce integration challenges.</span></span> <span data-ttu-id="24fb8-434">온-프레미스나 클라우드의 앱, 데이터 및 디바이스를 연결합니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-434">Connect apps, data and devices on-premises or in the cloud.</span></span></td>
</tr>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-435">Enterprise application services</span></span></td>
<td><span data-ttu-id="24fb8-436">회사 <a href="https://aws.amazon.com/workmail/" data-linktype="external">메일</a>, <a href="https://aws.amazon.com/workdocs/" data-linktype="external">워크 문서</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-436"><a href="https://aws.amazon.com/workmail/" data-linktype="external">WorkMail</a>, <a href="https://aws.amazon.com/workdocs/" data-linktype="external">WorkDocs</a></span></span></td>
<td><a href="https://products.office.com/" data-linktype="external"><span data-ttu-id="24fb8-437">Microsoft 365</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-438">클라우드와 다양한 디바이스에서 사용 가능한 통신, 이메일, 문서 관리를 제공하는 완전 통합 클라우드 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-438">Fully integrated Cloud service providing communications, email, document management in the cloud and available on a wide variety of devices.</span></span></td>
</tr>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-439">Gaming</span></span></td>
<td><span data-ttu-id="24fb8-440"><a href="https://aws.amazon.com/gamelift/" data-linktype="external">GameLift</a>, <a href="https://www.gamesparks.com/" data-linktype="external">GameSparks</a></span><span class="sxs-lookup"><span data-stu-id="24fb8-440"><a href="https://aws.amazon.com/gamelift/" data-linktype="external">GameLift</a>, <a href="https://www.gamesparks.com/" data-linktype="external">GameSparks</a></span></span></td>
<td><a href="https://playfab.com/" data-linktype="external"><span data-ttu-id="24fb8-441">PlayFab</span></span></a></td>
<td><span data-ttu-id="24fb8-442">전용 게임 서버를 호스팅하는 관리 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-442">Managed services for hosting dedicated game servers.</span></span></td>
</tr>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-443">Media transcoding</span></span></td>
<td><a href="https://aws.amazon.com/elastictranscoder/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-444">Elastic Transcoder</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/media-services/" data-linktype="external"><span data-ttu-id="24fb8-445">Media Services</span><span class="sxs-lookup"></span></a></td>
<td><span data-ttu-id="24fb8-446">여러 코드 변환 기술을 포함하여 고품질 비디오 스트리밍 서비스를 제공하는 서비스입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-446">Services that offer broadcast-quality video streaming services, including various transcoding technologies.</span></span></td>
</tr>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-447">Workflow</span></span></td>
<td><a href="https://aws.amazon.com/swf/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-448">Simple Workflow Service (SWF)</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/logic-apps/" data-linktype="external"><span data-ttu-id="24fb8-449">Logic Apps</span><span class="sxs-lookup"><span data-stu-id="24fb8-449">Logic Apps</span></span></a></td>
<td><span data-ttu-id="24fb8-450">온-프레미스 또는 클라우드에서 많은 SaaS 및 클라우드 기반 커넥터를 에코 시스템 앱, 데이터 및 장치를 연결 하기 위한 서버를 사용 하지 않는 기술입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-450">Serverless technology for connecting apps, data and devices anywhere, whether on-premises or in the cloud for large ecosystems of SaaS and cloud-based connectors.</span></span></td>
</tr>
<tr>
<td><span class="sxs-lookup"><span data-stu-id="24fb8-451">Hybrid</span></span></td>
<td><a href="https://aws.amazon.com/outposts/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-452">Outposts</span></span></a></td>
<td><a href="https://azure.microsoft.com/overview/azure-stack/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-453">Stack</span></span></a></td>
<td><span data-ttu-id="24fb8-454">Azure Stack는 회사 또는 서비스 공급자의 데이터 센터에서 Azure 서비스를 실행할 수 있도록 하는 하이브리드 클라우드 플랫폼입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-454">Azure Stack is a hybrid cloud platform that enables you to run Azure services in your company's or service provider's datacenter.</span></span> <span data-ttu-id="24fb8-455">개발자는 Azure Stack에서 앱을 빌드할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-455">As a developer, you can build apps on Azure Stack.</span></span> <span data-ttu-id="24fb8-456">그런 다음 Azure Stack 또는 Azure에 배포 하거나 Azure Stack 클라우드와 Azure 간의 연결을 활용 하는 진정한 하이브리드 앱을 빌드할 수 있습니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-456">You can then deploy them to either Azure Stack or Azure, or you can build truly hybrid apps that take advantage of connectivity between an Azure Stack cloud and Azure.</span></span></td>
</tr>
<tr>
<td><span data-stu-id="24fb8-457">Media</span></span></td>
<td><a href="https://aws.amazon.com/media-services" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-458">Elemental MediaConvert</span></span></a></td>
<td><a href="https://azure.microsoft.com/services/media-services/" data-linktype="external"><span class="sxs-lookup"><span data-stu-id="24fb8-459">Media Services</span></span></a></td>
<td><span data-ttu-id="24fb8-460">대규모로 비디오를 인덱싱하고, 패키지 하 고, 보호 하 고, 스트리밍하는 클라우드 기반 미디어 워크플로 플랫폼입니다.</span><span class="sxs-lookup"><span data-stu-id="24fb8-460">Cloud-based media workflow platform to index, package, protect, and stream video at scale.</span></span></td>
</tr>
</tbody>
</table>
