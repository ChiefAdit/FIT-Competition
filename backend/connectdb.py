import psycopg2
import os

instanceName = ""
port = 5432
db = "postgres"
user = "postgres"
password = ""

# sampel edit env di cloudrun langsung
# instance_name = os.environ.get("instances_name")

param = f"host='localhost' port={port} dbname='{db}' user='{user}' password= '{password}'"
#param = f"host='/cloudsql/{instanceName}' port={port} dbname='{db}' user='{user}' password= '{password}'"

conn = psycopg2.connect(param)

#test koneksi
def test_connection():
    try:
        conn = psycopg2.connect(param)
        print("Connection Success")
        return conn
    except Exception as e:
        print("Connection Failed", e)
        return None
    