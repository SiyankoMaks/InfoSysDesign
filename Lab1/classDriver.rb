class Driver
    # Инкапсуляция полей
    attr_reader :id, :last_name, :first_name, :middle_name, :experience
  
    # Конструктор для инициализации объекта Driver
    def initialize(id, last_name, first_name, middle_name, experience)
      @id = id
      self.last_name = last_name
      self.first_name = first_name
      self.middle_name = middle_name
      self.experience = experience
    end
  
    # Геттеры и сеттеры
    def last_name=(last_name)
      @last_name = last_name
    end
  
    def first_name=(first_name)
      @first_name = first_name
    end
  
    def middle_name=(middle_name)
      @middle_name = middle_name
    end
  
    def experience=(experience)
      @experience = experience
    end
  
    # Метод для вывода информации о водителе
    def info
      "Водитель: #{last_name} #{first_name} #{middle_name}, Стаж: #{experience} лет"
    end
  end

  driver = Driver.new(1, "Иванов", "Иван", "Иванович", 10)
  puts driver.info