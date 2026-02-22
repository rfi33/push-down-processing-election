SELECT COUNT(voter.id) as total_de_vote from voter;

SELECT vote_type::TEXT,count (vote.id) as nb_de_vote from vote group by vote_type;

SELECT candidate.name as candidate_name,count(vote.id) FILTER (WHERE vote.vote_type ='VALID') as valid_vote from vote inner join candidate
ON vote.candidate_id = candidate.id
GROUP BY candidate.name;

SELECT count (vote.id) FILTER (WHERE vote.vote_type = 'VALID') as valid_count,
        count (vote.id) FILTER (WHERE vote.vote_type = 'BLANK') as blank_count,
        count (vote.id) FILTER (WHERE vote.vote_type = 'NULL') as null_count
from vote;


SELECT COUNT(DISTINCT vote.voter_id) as votants,
       COUNT(DISTINCT voter.id) as total_electeurs,
       ROUND(COUNT(DISTINCT vote.voter_id)::NUMERIC/COUNT(DISTINCT voter.id)*100,2) as taux_de_participation
FROM voter
LEFT JOIN  vote on vote.voter_id = voter.id;

SELECT candidate.name as candidate_name,COUNT (vote.id) FILTER (WHERE vote_type = 'VALID') as valid_vote_count
from vote inner join candidate on vote.candidate_id = candidate.id
GROUP BY candidate.name
LIMIT 1;