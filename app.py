import falcon, jwt
import json
import psycopg2
from waitress import serve
from connectdb import conn

from api.users import GetAdminByID,LoginResourceAdmin, LoginResourceCustomer, RegisterResourceAdmin, CountAllUsersResource, CountUserAdmin, CountUserCustomer, GetAllUsersByID
from api.order import  MakeOrder, GetOrderByID
from api.tournamen import AddTournamen,Daftarturnamen,FilterTournamen, GetAllTournamen
from api.news import AddNews, DeleteNews, UpdateNews, GetNews
from api.match import AddMatch, AddMatchTeams, GetAllMatchesWithTeamsAndPoints
from api.partisipan import PartisipanResource
from api.afifa import afifah, read
# buat middleware jika ingin mengakses halaman harus login terlebih dahulu
 



# Inisialisasi aplikasi Falcon
app = falcon.API() 

#afifa
app.add_route('/addafifa', afifah())
app.add_route('/read', read())



#users
# Tambahkan route untuk login dan halaman terproteksi
app.add_route('/loginAdmin', LoginResourceAdmin())
app.add_route('/loginCustomer', LoginResourceCustomer())
#app.add_route('/protected', ProtectedResource())

app.add_route('/registeradmin', RegisterResourceAdmin())
app.add_route('/getusersbyid', GetAllUsersByID())
app.add_route('/getadminbyid', GetAdminByID())
app.add_route('/countallusers', CountAllUsersResource())
app.add_route('/countadmin', CountUserAdmin())
app.add_route('/countcustomer', CountUserCustomer())

#tournamen
app.add_route('/addtournamen', AddTournamen())
app.add_route('/daftarturnamen', Daftarturnamen())
app.add_route('/filtertournamen', FilterTournamen())
app.add_route('/getalltournamen', GetAllTournamen())

#news
app.add_route('/addnews', AddNews())
app.add_route('/deletenews', DeleteNews())
app.add_route('/updatenews', UpdateNews())
app.add_route('/getnews', GetNews())


#order
app.add_route('/makeorder', MakeOrder())
app.add_route('/getorderbyid', GetOrderByID())

#partisipan
app.add_route('/partisipan', PartisipanResource())

#match
app.add_route('/addmatch', AddMatch())
app.add_route('/addmatchteams', AddMatchTeams())
app.add_route('/getallmatcheswithteamsandpoints', GetAllMatchesWithTeamsAndPoints())

if __name__ == '__main__':
    serve(app, host='0.0.0.0', port=8000)
