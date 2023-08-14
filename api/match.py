import falcon, jwt
from datetime import datetime, timedelta, timezone
#import query order


from query.match import query_add_match, query_add_match_teams, query_get_all_matches_with_teams_and_points

class AddMatch:
    def on_post(self, req, resp):
        
        tournament_id =  req.media['tournament_id'],
        match_date = req.media['match_date'],
        match_location = req.media['match_location'], 
        
        if  not tournament_id or not match_date or not match_location :
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        add_match = query_add_match( tournament_id,  match_date, match_location)
        if add_match is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'Match berhasil ditambahkan'}

class AddMatchTeams:
    def on_post(self, req, resp):
        match_id = req.media['match_id'], 
        team_name =  req.media['team_name'],
        team_id = req.media['team_id']
        
        if not match_id or not team_name or not team_id:
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        add_match_teams = query_add_match_teams(match_id, team_name, team_id)
        if add_match_teams is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'Match Teams berhasil ditambahkan'}

class GetAllMatchesWithTeamsAndPoints:
    def on_post(self, req, resp):
        tournamen_id = req.media.get('tournamen_id')
        matches_data = query_get_all_matches_with_teams_and_points(tournamen_id)
        if matches_data:
            resp.status = falcon.HTTP_200
            resp.media = matches_data
        else:
            resp.status = falcon.HTTP_404
            resp.media = {'message': 'Tidak ada match'}
