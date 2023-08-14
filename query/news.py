import connectdb


#quey add news dari table adit_fit_news
def query_add_news(judul, gambar, isi, kategori, tanggal): 
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_news (judul, gambar, isi, kategori, tanggal) VALUES (%s, %s, %s, %s, %s)", (judul, gambar, isi, kategori, tanggal))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#query delete news dari table adit_fit_news
def query_delete_news(id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("DELETE FROM adit_fit_news WHERE id = %s", (id,))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#query update news dari table adit_fit_news
def query_update_news(id, judul, gambar, isi):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("UPDATE adit_fit_news SET judul = %s, gambar = %s, isi = %s WHERE id = %s", (judul, gambar, isi, id))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#query get all news dari table adit_fit_news
def query_get_all_news():
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT s.id, s.judul, s.gambar, s.isi, s.kategori, s.tanggal, u.name FROM adit_fit_news s JOIN adit_fit_users u ON u.name = u.id WHERE u.role = 'admin'")
        news = cur.fetchall()
        cur.close()

        # Ubah data hasil query menjadi key-value (dictionary)
        news_list = []
        columns = [desc[0] for desc in cur.description]
        for row in news:
            news_dict = dict(zip(columns, row))
            news_list.append(news_dict)

        return news_list
    else:
        print("Connection Failed")
        return None
