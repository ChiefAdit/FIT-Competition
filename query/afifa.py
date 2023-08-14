import connectdb
from datetime import datetime
import json

#query for make match
def query_note( name, status): 
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        current_date = datetime.now().date()
        cur.execute("INSERT INTO adit_afifa (name, status, date) VALUES (%s, %s, %s )", (name, status, current_date))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#query for get all 
def query_getall():
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT * FROM adit_afifa ORDER BY date DESC")
        rows = cur.fetchall()
        cur.close()

        # Convert the result to a list of dictionaries
        result = []
        for row in rows:
            entry = {
                "name": row[0],
                "status": row[1],
                "date": row[2].strftime("%Y-%m-%d")  # Format tanggal menjadi string
            }
            result.append(entry)

        return result
    else:
        print("Connection Failed")
        return False