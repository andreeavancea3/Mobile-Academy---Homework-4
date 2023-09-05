# Mobile-Academy---Homework-4
Dark Mode
Implement a web app that is able to support Dark Mode display.
Endpoints:
1. GET /api/cars
- displays two things (with a DTO)
- a list of cars from the database (Controller, Service, Repository required)
- information about whether dark mode is true or false
- may receive the "darkmode" cookie (in a client header) and provide the response accordingly (in the previous DTO)
2. PUT /api/dark-mode
- receives in body a DTO with dark mode setting (true or false)
{
“darkMode”: true
}
- sends a header response with user preference (“Set - Cookie” header)
