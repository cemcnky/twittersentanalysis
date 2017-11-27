// fetch execution variables
var response = connector.getVariable("response");

print(response);
// use camunda-spin jsonPath to test if date is a holiday
//!holidays.jsonPath(query).elementList().isEmpty();
