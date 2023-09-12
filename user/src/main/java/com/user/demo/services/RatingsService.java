package com.user.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.model.Ratings;
import com.user.demo.repository.RatingRepository;

@Service
public class RatingsService {
	@Autowired
	RatingRepository ratingRepository;

	// To get all the ratings
	public List<Ratings> getallRatings() {
		return ratingRepository.findAll();
	}

	// To add a rating
	public Ratings addRating(Ratings ratings) {
		return ratingRepository.save(ratings);
	}

	// To get ratings of specific washer
	public List<Ratings> washerSpecific(String washerName) {
		List<Ratings> washerSpecificRatings = ratingRepository.findAll().stream()
				.filter(x -> x.getWasherName().contains(washerName)).collect(Collectors.toList());
		return washerSpecificRatings;
	}

//	// To delete a rating
//	public String deleteRating(int id) {
//		ratingRepository.deleteById(id);
//		return "Rating with ID -> " + id + " deleted successfully";
//	}

}
