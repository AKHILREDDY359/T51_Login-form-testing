Feature: Login Validation

  Scenario Outline: Login test cases
    Given user is on Practice Test login page
    When user logs in with username "<username>" and password "<password>"
    Then "<result>" should be displayed

  Examples:
    #VALID LOGIN (PASS)
    | username | password     | result  |
    | student  | Password123  | success |

   # INVALID PASSWORD (PASS)
    | student  | wrongpass    | invalid credentials |

    #INVALID USERNAME (PASS)
    | wronguser | Password123 | invalid credentials |

  #  EMPTY USERNAME (PASS)
    |          | Password123  | username required |

  #  EMPTY PASSWORD (PASS)
    | student  |              | password required |

   # BOTH EMPTY (PASS)
    |          |              | both required |

    #INTENTIONAL FAILURES (for report demo)

    # Wrong password but expecting success → FAIL
    | student  | wrongpass    | success |

    # Wrong username but expecting success → FAIL
    | wronguser | Password123 | success |

    # Empty fields but expecting success → FAIL
    |          |              | success |

    # Valid login but expecting failure → FAIL
    | student  | Password123  | invalid credentials |

    # Valid login but expecting username required → FAIL
    | student  | Password123  | username required |

    # Boundary / weird inputs (mostly PASS)
    | STUDENT  | Password123  | invalid credentials |
    | student  | PASSWORD123  | invalid credentials |
    | stud     | Password123  | invalid credentials |
    | studentstudent | Password123 | invalid credentials |
    | student  | 123456       | invalid credentials |
