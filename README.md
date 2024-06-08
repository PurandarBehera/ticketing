# ticketing
# create User
curl --location 'http://localhost:8080/api/users/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
}'

# get User

curl --location 'http://localhost:8080/api/users/1'

# delete User
curl --location --request DELETE 'http://localhost:8080/api/users/1'

# purchase Ticket
curl --location 'http://localhost:8080/api/tickets/purchase' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fromLocation": "London",
    "toLocation": "France",
    "price": 20.0,
    "seatSection": "A",
    "userEmail": "john.doe@example.com"
}'
# get Ticket
curl --location 'http://localhost:8080/api/tickets/1'

# get Ticket By Section
curl --location 'http://localhost:8080/api/tickets/section/B'

# update ticket
curl --location --request PUT 'http://localhost:8080/api/tickets/1/update-section' \
--header 'Content-Type: application/json' \
--data '{
    "seatSection": "B",
    "seatNumber": 7
}'

# delete ticket
curl --location --request DELETE 'http://localhost:8080/api/tickets/1'
