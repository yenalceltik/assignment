package com.openpayd.foreignexchange.dao;

import com.openpayd.foreignexchange.model.ConversionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("conversionDetailDAO")
public interface ConversionDetailDAO extends JpaRepository<ConversionDetail, String>
{
}
