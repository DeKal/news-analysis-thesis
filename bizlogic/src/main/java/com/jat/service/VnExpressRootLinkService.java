package com.jat.service;

import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jat.persistence.entity.VNExpressRootLink;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service("VnExpressRootLinkService")
@Transactional
public class VnExpressRootLinkService {
	@Autowired(required = true)
	private MongoTemplate mongoTemplate;
	public VNExpressRootLink findLink(String strLink){
		VNExpressRootLink link = mongoTemplate.findOne(
		Query.query(Criteria.where("link").is(strLink)), VNExpressRootLink.class);
		
		return link;
	}
	public Boolean addLink(VNExpressRootLink link) {
		try {
			if (!(link.getId() != null && link.getId() != "")) {
				// Set a new value to the empid property first since it's blank
				link.setId(UUID.randomUUID().toString());
				// Insert to db
				mongoTemplate.save(link);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public List<VNExpressRootLink> getLink(){
		// Execute the query and find all matching entries
		List<VNExpressRootLink> links = mongoTemplate.findAll(VNExpressRootLink.class);

		return links;
	}
}
