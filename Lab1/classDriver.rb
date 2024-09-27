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

  # Статический метод для создания объекта Driver с проверкой всех полей
  def self.create_driver(id, last_name, first_name, middle_name, experience)
    raise 'ID должен быть положительным числом' unless id.is_a?(Integer) && id > 0
    validate_last_name(last_name)
    validate_first_name(first_name)
    validate_middle_name(middle_name)
    validate_experience(experience)
    
    new(id, last_name, first_name, middle_name, experience)
  end

  # Валидации для полей
  def self.validate_last_name(last_name)
    raise 'Фамилия не может быть пустой' if last_name.nil? || last_name.strip.empty?
  end

  def self.validate_first_name(first_name)
    raise 'Имя не может быть пустым' if first_name.nil? || first_name.strip.empty?
  end

  def self.validate_middle_name(middle_name)
    raise 'Отчество не может быть пустым' if middle_name.nil? || middle_name.strip.empty?
  end

  def self.validate_experience(experience)
    raise 'Стаж должен быть положительным числом' unless experience.is_a?(Integer) && experience >= 0
  end

  # Сеттеры с валидацией
  def last_name=(last_name)
    self.class.validate_last_name(last_name)
    @last_name = last_name
  end

  def first_name=(first_name)
    self.class.validate_first_name(first_name)
    @first_name = first_name
  end

  def middle_name=(middle_name)
    self.class.validate_middle_name(middle_name)
    @middle_name = middle_name
  end

  def experience=(experience)
    self.class.validate_experience(experience)
    @experience = experience
  end

  # Метод Информация о водителе
  def info
    "Водитель: #{last_name} #{first_name} #{middle_name}, Стаж: #{experience} лет"
  end
end

# Пример
begin
  driver = Driver.create_driver(1, "Иванов", "Иван", "Иванович", 10)
  puts driver.info
rescue => e
  puts "Ошибка: #{e.message}"
end

# Пример плохой:
# driver = Driver.create_driver(1, "", "Иван", "Иванович", 10)
