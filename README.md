# Head

Some text

## Item

Some text

### Rest Client

 Rest client calls
 For more information, see <https://marketplace.visualstudio.com/items?itemName=humao.rest-client>.

````text
POST http://localhost:9000/send-message HTTP/1.1
content-type: application/json

{
    "messageContent": "For all Sample message content"
}
````text

````text
POST http://localhost:9000/send-private-message/0477a937-5cc3-4e62-a4de-c6c58094b061 HTTP/1.1
content-type: application/json

{
    "messageContent": "For user Sample message content"
}
````text
