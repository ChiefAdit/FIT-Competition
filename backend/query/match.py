import connectdb



#query for make match
def query_add_match( tournament_id,  match_date, match_location): 
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_match (tournament_id, match_date, match_location) VALUES (%s, %s, %s )", (tournament_id, match_date, match_location, ))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
#query untuk memasukan nama teams ke sebuah tournamen
def query_add_teams ():
    conn = connectdb.test_connection()
    cur = conn.cursor()
    cur.execute("INSERT INTO adit_fit_match_teams (match_id, team_name, team_id)")

#query for match teams
def query_add_match_teams(match_id, team_name, team_id): 
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_match_teams (match_id, team_name, team_id) VALUES (%s, %s, %s)", (match_id, team_name, team_id))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#query menampilkan semua match beserta teamnya dan scorenya
def query_get_all_matches_with_teams_and_points(tournamen_id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        # Gunakan JOIN untuk menggabungkan tabel adit_fit_match dan adit_fit_match_teams
        cur.execute("SELECT mt.match_id, m.match_date, m.match_location, mt.team_name, mt.poin, p.gambar, mt.jam, z.bracket FROM adit_fit_match_teams mt JOIN adit_fit_match m ON mt.match_id = m.id JOIN adit_fit_partisipan p ON mt.team_id = p.id JOIN adit_fit_tournamen z ON z.id = m.tournament_id WHERE m.tournament_id = %s ORDER BY mt.match_id, mt.id", (tournamen_id,))
        matches = cur.fetchall()
        cur.close()

        # Proses data hasil query menjadi JSON yang diinginkan
        matches_data = []
        current_match_id = None
        match_info = {}

        for match_id, match_date, match_location, team_name, point, team_gambar, match_time, bracket in matches:
            if match_id != current_match_id:
                if match_info:
                    matches_data.append(match_info)
                match_info = {
                    "id": str(match_id),
                    "date": str(match_date),
                    "time": str(match_time),
                    "bracket": str(bracket),
                    "teams": []
                }
                current_match_id = match_id

            match_info["teams"].append({"name": team_name, "point": str(point), "gambar": team_gambar})

        if match_info:  # Append the last match_info to matches_data
            matches_data.append(match_info)

        response_data = {"matches": matches_data}

        
        return response_data
    else:
        print("Connection Failed")
        return None






 #query make match