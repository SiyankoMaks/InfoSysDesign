require 'json'

class Driver
    # Инкапсуляция полей
    attr_reader :id, :last_name, :first_name, :middle_name, :experience
  
    # Конструктор для инициализации объекта Driver
    def initialize(*args)
        # Если один аргумент
        if args.size == 1
          input = args[0]
          if input.is_a?(String)
            # JSON-строка
            begin
              data = JSON.parse(input)
              initialize_from_hash(data)
            rescue JSON::ParserError
              # Обработка строки в формате "ФИО, Стаж"
              initialize_from_string(input)
            end
          elsif input.is_a?(Hash)
            # Хэш
            initialize_from_hash(input)
          else
            raise 'Неподдерживаемый формат входных данных'
          end
        # Все параметры
        elsif args.size == 5
          @id = validate_id(args[0]) # Валидация id
          self.last_name = args[1]
          self.first_name = args[2]
          self.middle_name = args[3]
          self.experience = args[4]
        else
          raise 'Неправильное количество аргументов для создания объекта Driver'
        end
    end
      
  
    # Статический метод для создания объекта Driver с проверкой всех полей
    def self.create_driver(*args)
        new(*args)
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
  
    # Метод Информация о водителе
    def info
      "Водитель: #{last_name} #{first_name} #{middle_name}, Стаж: #{experience} лет"
    end

    # Метод для инициализации из хэша
    def initialize_from_hash(data)
        @id = validate_id(data['id'] || data[:id])
        self.last_name = data['last_name'] || data[:last_name]
        self.first_name = data['first_name'] || data[:first_name]
        self.middle_name = data['middle_name'] || data[:middle_name]
        self.experience = data['experience'] || data[:experience]
    end

    # Метод для инициализации из строки "Фамилия Имя Отчество, Стаж"
    def initialize_from_string(input)
        parts = input.split(',')
        raise 'Некорректный формат строки' unless parts.size == 2
    
        full_name = parts[0].strip.split
        raise 'Некорректный формат имени' unless full_name.size == 3
    
        self.last_name = full_name[0]
        self.first_name = full_name[1]
        self.middle_name = full_name[2]
        self.experience = parts[1].strip.to_i
    end

end
  
  # Примеры
  # отдельные параметры
driver1 = Driver.create_driver(1, "Иванов", "Иван", "Иванович", 10)
puts driver1.info

# JSON-строка
json_input = '{"id": 2, "last_name": "Петров", "first_name": "Петр", "middle_name": "Петрович", "experience": 15}'
driver2 = Driver.create_driver(json_input)
puts driver2.info

# хэш
hash_input = { id: 3, last_name: "Сидоров", first_name: "Сидор", middle_name: "Сидорович", experience: 5 }
driver3 = Driver.create_driver(hash_input)
puts driver3.info

# строка в формате "ФИО, Стаж"
string_input = "Кузнецов Алексей Алексеевич, 12"
driver4 = Driver.create_driver(string_input)
puts driver4.info
  
# Пример плохой:
# driver = Driver.create_driver(1, "", "Иван", "Иванович", 10)