import falcon, jwt
from datetime import datetime, timedelta, timezone
#import query order

from query.order import  query_make_order, query_get_order_by_id



class MakeOrder:
    def on_post(self,req,resp):
        user_id = req.media.get('user_id')
        tournamen_id = req.media.get('tournamen_id')
        
        if not user_id or not tournamen_id :
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        
        order = query_make_order(user_id, tournamen_id)
        if order is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'Order berhasil'}

class GetOrderByID:
    def on_post(self, req, resp):
        user_id = req.media.get('user_id')

        if not user_id:
            resp.status = falcon.HTTP_BAD_REQUEST
            return

        orders = query_get_order_by_id(user_id)
        if orders:
            # Convert the tuple result into a list of dictionaries
            orders_data = []
            for order in orders:
                order_data = {
                    "id": order[0],
                    "user_id": order[1],
                    "tournament_id": order[2],
                    "status": order[3],
                    "gambar": order[4],
                    "id" : order[5],
                    "name": order[6],
                    "description": order[7],
                    "fee": order[8],
                    "lokasi": order[9],
                    "jenis": order[10],
                    "partisipan": order[11],
                    "anggota_team": order[12],
                    "whatsapp": order[13],
                    "linkgroup": order[14],
                    "tanggal": order[15],
                    
                }
                orders_data.append(order_data)

            resp.status = falcon.HTTP_200
            resp.media = {'message': 'Orders berhasil ditampilkan', 'orders': orders_data}
        else:
            resp.status = falcon.HTTP_404
            resp.media = {'message': 'Data order tidak ditemukan'}