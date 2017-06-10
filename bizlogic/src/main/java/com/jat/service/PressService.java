package com.jat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jat.persistence.entity.Press;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service("PressService")
@Transactional
public class PressService {
	@Autowired(required = true)
	private MongoTemplate mongoTemplate;

	/**
	 * Retrieves all Pres
	 */
	public List<Press> listPress() {

		// Execute the query and find all matching entries
		List<Press> Press = mongoTemplate.findAll(Press.class);

		return Press;
	}

	/**
	 * Retrieves a single Press
	 */
	public Press getPress(String id) {
		Press Press = new Press();
		// Find an entry where empid matches the id
		DBObject query = new BasicDBObject();
		query.put("id", id);
		DBObject cursor = mongoTemplate.getDb().getCollection("presses").findOne(query);
		Press.setId(cursor.get("id").toString());
		Press.setLink(cursor.get("link").toString());
		Press.setContent(cursor.get("content").toString());
		// Press.setComment(cursor.get("salary"));

		return Press;
	}

	/**
	 * Adds a new Press
	 */
	public Boolean addPress(Press Press) {

		try {
			if (!(Press.getId() != null && Press.getId() != "")) {
				// Set a new value to the empid property first since it's blank
				Press.setId(UUID.randomUUID().toString());
							
			}
			mongoTemplate.save(Press);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Press findPress(String link) {
		Query findQuery = new Query();
		Criteria findCriteria = Criteria.where("link").is(link);
		findQuery.addCriteria(findCriteria);
		Press press = mongoTemplate.findOne(findQuery, Press.class);

		return press;
	}

	public List<Press> listNoContentPress() {
		// TODO Auto-generated method stub

		List<Press> lPress = mongoTemplate.find(Query.query(Criteria.where("content").is(null)), Press.class);

		return lPress;
	}

}
