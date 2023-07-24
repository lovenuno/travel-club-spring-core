package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;

import java.util.*;
import java.util.stream.Collectors;

public class ClubMapStore implements ClubStore {

    private Map<String, TravelClub> clubMap;

    public ClubMapStore(){
        this.clubMap = new LinkedHashMap<>();
    }

    @Override
    public String create(TravelClub club) {
        clubMap.put(club.getId(), club);
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {

        return clubMap.get(clubId);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {

        List<Map.Entry<String, TravelClub>> retList = new ArrayList<>();

        for (Map.Entry<String, TravelClub> entry : clubMap.entrySet()) {
            if(entry.getValue().equals(name)) {
                retList.add(entry);
            }
        }
        //return retList;

        return clubMap.values().stream()
                .filter( club -> club.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        //return clubMap.containsKey(clubId);
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}