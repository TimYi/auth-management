package com.shz.pay.core;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RefundRecordRepository extends PagingAndSortingRepository<RefundRecord, String> {

	RefundRecord getByRefundId(String refundId);
}
