package com.linksame.crm.erp.oa.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOaExamineTravel<M extends BaseOaExamineTravel<M>> extends Model<M> implements IBean {

	public void setTravelId(java.lang.Integer travelId) {
		set("travel_id", travelId);
	}
	
	public java.lang.Integer getTravelId() {
		return getInt("travel_id");
	}

	public void setExamineId(java.lang.Integer examineId) {
		set("examine_id", examineId);
	}
	
	public java.lang.Integer getExamineId() {
		return getInt("examine_id");
	}

	public void setStartAddress(java.lang.String startAddress) {
		set("start_address", startAddress);
	}
	
	public java.lang.String getStartAddress() {
		return getStr("start_address");
	}

	public void setStartTime(java.util.Date startTime) {
		set("start_time", startTime);
	}
	
	public java.util.Date getStartTime() {
		return get("start_time");
	}

	public void setEndAddress(java.lang.String endAddress) {
		set("end_address", endAddress);
	}
	
	public java.lang.String getEndAddress() {
		return getStr("end_address");
	}

	public void setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
	}
	
	public java.util.Date getEndTime() {
		return get("end_time");
	}

	public void setTraffic(java.math.BigDecimal traffic) {
		set("traffic", traffic);
	}
	
	public java.math.BigDecimal getTraffic() {
		return get("traffic");
	}

	public void setStay(java.math.BigDecimal stay) {
		set("stay", stay);
	}
	
	public java.math.BigDecimal getStay() {
		return get("stay");
	}

	public void setDiet(java.math.BigDecimal diet) {
		set("diet", diet);
	}
	
	public java.math.BigDecimal getDiet() {
		return get("diet");
	}

	public void setOther(java.math.BigDecimal other) {
		set("other", other);
	}
	
	public java.math.BigDecimal getOther() {
		return get("other");
	}

	public void setMoney(java.math.BigDecimal money) {
		set("money", money);
	}
	
	public java.math.BigDecimal getMoney() {
		return get("money");
	}

	public void setVehicle(java.lang.String vehicle) {
		set("vehicle", vehicle);
	}
	
	public java.lang.String getVehicle() {
		return getStr("vehicle");
	}

	public void setTrip(java.lang.String trip) {
		set("trip", trip);
	}
	
	public java.lang.String getTrip() {
		return getStr("trip");
	}

	public void setDuration(java.math.BigDecimal duration) {
		set("duration", duration);
	}
	
	public java.math.BigDecimal getDuration() {
		return get("duration");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}
	
	public java.lang.String getDescription() {
		return getStr("description");
	}

	public void setBatchId(java.lang.String batchId) {
		set("batch_id", batchId);
	}
	
	public java.lang.String getBatchId() {
		return getStr("batch_id");
	}

}
