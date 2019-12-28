# spring BeanUtils.copyProperties() // VO 간단 복사 방안

import org.apache.commons.beanutils.BeanUtils;

CommonVO commonvo  = new CommonVO();
CommonVO clonevo  = new CommonVO();

BeanUtils.copyProperties(clonevo, commonvo);