# API Endpoints

## Users


### GET /users

#### Query Parameters

- None

#### Response

##### 200 OK

```json
[
    {
        "id": 1,
        "username": "jason",
        "createdAt": "2024-01-28T21:05:35.499161",
        "roles": [
            "USER"
        ]
    }
]
```

### POST /users

#### Request

```json
{
    "username": "jason"
}
```
- `username` (string, optional) - The username of the user to create. 
If not provided, a random username will be generated.

#### Response

##### 201 Created

```json
{
    "id": 1,
    "username": "jason",
    "createdAt": "2024-01-28T21:05:35.499161",
    "roles": [
        "USER"
    ]
}
```
It returns a JWT in the ``Jwt`` header.

### GET /users/{id}

#### Path Parameters

- `id` (integer) - The ID of the user to retrieve.

#### Response

##### 200 OK

```json
{
    "id": 1,
    "username": "jason",
    "createdAt": "2024-01-28T21:05:35.499161",
    "roles": [
        "USER"
    ]
}
```

### PUT /users/{id}

#### Path Parameters

- `id` (integer) - The ID of the user to update.

#### Request

```json
{
    "username": "jason"
}
```

#### Response

##### 200 OK

```json
{
    "id": 1,
    "username": "jason",
    "createdAt": "2024-01-28T21:05:35.499161",
    "roles": [
        "USER"
    ]
}
```

### DELETE /users/{id}

#### Path Parameters

- `id` (integer) - The ID of the user to delete.

#### Response

##### 204 No Content


## Prayers


### GET /prayers

#### Query Parameters
- `prayer-request-id` (integer, optional) - The ID of the prayer request to filter by.
- `believer-id` (integer, optional) - The ID of the believer to filter by.

#### Response

```json
[
    {
        "id": 1,
        "prayerRequestId": 1,
        "prayerRequest": {
            "id": 1,
            "requester": {
                "id": 4,
                "username": "ray",
                "createdAt": "2024-01-21T22:16:29.570486",
                "roles": [
                    "USER"
                ]
            },
            "request": "This is a request for pray",
            "createdAt": "2024-01-21T22:16:14.359559"
        },
        "believer": {
            "id": 3,
            "username": "jason",
            "createdAt": "2024-01-21T22:15:30.497988",
            "roles": [
                "USER"
            ]
        },
        "content": "I'm praying for u bro",
        "createdAt": "2024-02-11T17:41:23.404281268"
    }
]
```

### POST /prayers

#### Request
```json
{
  "prayerRequestId": 1,
  "content": "I'm praying for u bro"
}
```
To skip a prayer request, you can create a prayer with an empty content.

#### Response
```json
{
    "id": 8,
    "prayerRequestId": 1,
    "prayerRequest": {
        "id": 1,
        "requester": {
            "id": 4,
            "username": "ray",
            "createdAt": "2024-01-21T22:16:29.570486",
            "roles": [
                "USER"
            ]
        },
        "request": "This is a request for pray",
        "createdAt": "2024-01-21T22:16:14.359559"
    },
    "believer": {
        "id": 3,
        "username": "jason",
        "createdAt": "2024-01-21T22:15:30.497988",
        "roles": [
            "USER"
        ]
    },
    "content": "I'm praying for u bro",
    "createdAt": "2024-02-11T17:41:23.404281268"
}
```

### GET /prayers/{id}

#### Path Parameters
- `id` (integer) - The ID of the prayer to retrieve.

#### Response
```json
{
    "id": 8,
    "prayerRequestId": 1,
    "prayerRequest": {
        "id": 1,
        "requester": {
            "id": 4,
            "username": "ray",
            "createdAt": "2024-01-21T22:16:29.570486",
            "roles": [
                "USER"
            ]
        },
        "request": "This is a request for pray",
        "createdAt": "2024-01-21T22:16:14.359559"
    },
    "believer": {
        "id": 3,
        "username": "jason",
        "createdAt": "2024-01-21T22:15:30.497988",
        "roles": [
            "USER"
        ]
    },
    "content": "I'm praying for u bro",
    "createdAt": "2024-02-11T17:41:23.404281268"
}
```

## Prayer Requests


### GET /prayer-requests

#### Query Parameters

- `requester-id` (integer, optional) - The ID of the requester to filter by.
- `recommended-for-id` (integer, optional) - The ID of the user to recommend prayer requests for.

#### Response

```json
[
    {
        "id": 1,
        "requester": {
            "id": 4,
            "username": "ray",
            "createdAt": "2024-01-21T22:16:29.570486",
            "roles": [
                "USER"
            ]
        },
        "request": "This is a request for pray",
        "createdAt": "2024-01-21T22:16:14.359559"
    }
]
```

### POST /prayer-requests

#### Request

```json
{
    "request": "This is a request for pray"
}
```

#### Response

#### 201 Created

```json
{
    "id": 1,
    "requester": {
        "id": 4,
        "username": "ray",
        "createdAt": "2024-01-21T22:16:29.570486",
        "roles": [
            "USER"
        ]
    },
    "request": "This is a request for pray",
    "createdAt": "2024-01-21T22:16:14.359559"
}
```

### GET /prayer-requests/{id}

#### Path Parameters
- `id` (integer) - The ID of the prayer request to retrieve.

#### Response

```json
{
    "id": 1,
    "requester": {
        "id": 4,
        "username": "ray",
        "createdAt": "2024-01-21T22:16:29.570486",
        "roles": [
            "USER"
        ]
    },
    "request": "This is a request for pray",
    "createdAt": "2024-01-21T22:16:14.359559"
}
```

