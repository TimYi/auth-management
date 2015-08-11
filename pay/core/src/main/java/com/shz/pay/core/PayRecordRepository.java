package com.shz.pay.core;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PayRecordRepository extends PagingAndSortingRepository<PayRecord, String> {

	PayRecord findByChargeId(String chargeId);
}
