class Driver
  # Инкапсуляция полей
  attr_reader :id, :last_name, :first_name, :middle_name, :experience

  # Конструктор для инициализации объекта Driver
  def initialize(id, last_name, first_name, middle_name, experience)
    @id = validate_id(id) # валидация id
    self.last_name = last_name
    self.first_name = first_name
    self.middle_name = middle_name
    self.experience = experience
  end

  # Статический метод для создания объекта Driver с проверкой всех полей
  def self.create_driver(id, last_name, first_name, middle_name, experience)
    new(id, last_name, first_name, middle_name, experience)
  end

  # Валидации для полей
  def validate_id(id)
    raise 'ID должен быть положительным числом' unless id.is_a?(Integer) && id > 0
    id
  end

  def validate_name(name, field_name)
    raise "#{field_name} не может быть пустым" if name.nil? || name.strip.empty?
    name
  end

  def validate_experience(experience)
    raise 'Стаж должен быть положительным числом' unless experience.is_a?(Integer) && experience >= 0
    experience
  end

  # Сеттеры с валидацией
  def last_name=(last_name)
    @last_name = validate_name(last_name, "Фамилия")
  end

  def first_name=(first_name)
    @first_name = validate_name(first_name, "Имя")
  end

  def middle_name=(middle_name)
    @middle_name = validate_name(middle_name, "Отчество")
  end

  def experience=(experience)
    @experience = validate_experience(experience)
  end
  
  #Методы
  # Полная информация о водителе
  def info
    "Полная информация: Водитель: #{last_name} #{first_name} #{middle_name}, Стаж: #{experience} лет, ID: #{id}"
  end
  
  # Краткая информация о водителе
  def short_info
    "Краткая информация: Водитель: #{last_name} #{first_name[0]}.#{middle_name[0]}."
  end

  # Метод для сравнения двух объектов Driver
  def self.compare_objects(obj1, obj2)
    obj1.id == obj2.id &&
    obj1.last_name == obj2.last_name &&
    obj1.first_name == obj2.first_name &&
    obj1.middle_name == obj2.middle_name &&
    obj1.experience == obj2.experience
  end

end

# Пример
begin
  driver1 = Driver.create_driver(1, "Иванов", "Иван", "Иванович", 10)
  driver2 = Driver.create_driver(2, "Петров", "Петр", "Петрович", 8)
  driver3 = Driver.create_driver(1, "Иванов", "Иван", "Иванович", 10)
  
  # Полная и краткая версия информации
  puts driver1.info
  puts driver1.short_info
  
  # Сравнение объектов
  puts "Сравнение driver1 и driver2: #{Driver.compare_objects(driver1, driver2)}" # false
  puts "Сравнение driver1 и driver3: #{Driver.compare_objects(driver1, driver3)}" # true
rescue => e
  puts "Ошибка: #{e.message}"
end

# Пример плохой:
# driver = Driver.create_driver(1, "", "Иван", "Иванович", 10)
